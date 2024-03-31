# -*- coding: utf-8 -*-

import itertools
import os
import mimetypes
import shutil
import uuid
import requests
from requests.exceptions import HTTPError
from dotenv import load_dotenv
import pandas as pd
import database
import file_server

# 시험 목적으로 실행합니다
# (완료 시 실행 내용을 되돌립니다)
DRY_RUN = True
DRY_RUN_SIZE = 256

def download(url: str, path: str):
    """URL로부터 지정한 파일을 `path`에 저장합니다

    Parameters
    ----------
    url : str
        URL
    path : str
        저장할 파일의 경로
    """

    request = requests.get(url, stream=True, timeout=8)
    request.raise_for_status()

    with open(path, "wb") as file:
        shutil.copyfileobj(request.raw, file)

def update_image_url(url: str) -> str:
    """FMA의 이미지 URL을 현재 형식으로 변경합니다

    https://github.com/mdeff/fma/issues/51

    Parameters
    ----------
    url : str
        이전 형식의 URL

    Returns
    -------
    str
        현재 형식의 URL
    """
    old_prefix = "https://freemusicarchive.org/file/"
    new_prefix = "https://freemusicarchive.org/image?file="
    new_suffix = "&width=290&height=290&type=image"

    assert url.startswith(old_prefix)

    return f"{new_prefix}{url[len(old_prefix):]}{new_suffix}"

# .env를 읽습니다
load_dotenv()

# 삽입할 곡의 정보를 읽습니다
artists = pd.read_csv(
    "dataset/fma/data/fma_metadata/raw_tracks.csv",
    skipinitialspace=True,
    usecols=[
        "track_id",
        "artist_id",
        "track_image_file",
        "track_information",
        "track_title"
    ]
)

insert = []
image_insert = []
file_insert = []
image_insert_id = itertools.count(7638)
file_insert_id = itertools.count(1)

for index, row in artists.iterrows():
    # 아티스트 이미지를 다운로드하기 전 URL을 정리합니다
    download_url = row["artist_image_file"]

    filename = download_url[
        len("https://freemusicarchive.org/file/images/albums/"):
    ] if download_url.startswith(
        "https://freemusicarchive.org/file/images/albums/"
    ) else None
    download_url = update_image_url(
        download_url
    ) if download_url.startswith(
        "https://freemusicarchive.org/file/images/albums/"
    ) else None

    # 유효한 이미지가 주어진 경우 다운로드를 진행합니다
    if filename is not None:
        download_path = os.path.join("dataset", "images", "albums", filename)

        try:
            # 파일이 존재하지 않을 시에만 진행합니다
            if not os.path.exists(download_path) and download_url is not None:
                print(f"Downloading from {download_url}")
                download(download_url, download_path)
            else:
                print(f"Using downloaded {filename}")

            assert mimetypes.guess_type(download_path)[0].startswith("image/")

            # file entity에 삽입할 데이터를 준비합니다
            file_id = next(image_insert_id)
            file_insert.append(
                {
                    "id": file_id,
                    "file_name": str(uuid.uuid4()),
                    "original_file_name": filename,
                    "file_path": download_path
                }
            )
        except HTTPError:
            print(f"  Downloading {filename} failed")
            file_id = None # pylint: disable=invalid-name
    else:
        file_id = None # pylint: disable=invalid-name

    # 삽입할 데이터를 준비합니다
    insert.append(
        {
            "id": row["artist_id"],
            "profile_image_id": file_id,
            "introduction": str(row["artist_bio"])[:255],
            "nickname": str(row["artist_name"]),
            "username": str(row["artist_handle"]),
        }
    )

    # 시험 목적으로 실행 시 일부 데이터만 가져옵니다
    if DRY_RUN and index >= DRY_RUN_SIZE:
        break

# MinIO client를 준비합니다
minio_client = file_server.get_client()
saved_files = []

# MySQL (MariaDB) 데이터베이스에 연결합니다
try:
    with database.get_connection() as connection:
        with connection.cursor() as cursor:
            connection.autocommit = False

            print("Inserting image records")

            # 이미지 파일 정보를 삽입합니다
            cursor.executemany(
                "insert into image "
                "(id, saved_file_name, original_file_name) "
                "values (%s, %s, %s)",
                [
                    (
                        row["id"],
                        row["file_name"],
                        row["original_file_name"]
                    ) for row in file_insert
                ]
            )

            print("Inserting artist records")

            # 아티스트 정보를 삽입합니다
            cursor.executemany(
                "insert into artist "
                "(id, age, sex, profile_image_id, introduction, nickname, username, role) "
                "values (%s, 20, 'F', %s, %s, %s, %s, 'GUEST')",
                [
                    (
                        row["id"],
                        row["profile_image_id"],
                        row["introduction"],
                        row["nickname"],
                        row["username"]
                    ) for row in insert
                ]
            )

            print("Updating artists' profile images")

            # 이미지 파일을 client에 업로드합니다
            for image_file in file_insert:
                print(f"Uploading {image_file['original_file_name']} ({image_file['file_name']})")

                file_server.upload(
                    image_file["file_path"],
                    "image",
                    image_file["file_name"],
                    minio_client
                )
                saved_files.append(image_file["file_name"])

            # 시험 목적으로 사용 시 commit을 하지 않습니다
            if not DRY_RUN:
                connection.commit()

except Exception as exception: # pylint: disable=broad-exception-caught
    for image_file in saved_files:
        file_server.delete("image", image_file, minio_client)

    raise exception

# 시험 목적으로 사용 시 업로드한 이미지를 삭제합니다
if DRY_RUN:
    for image_file in saved_files:
        file_server.delete("image", image_file, minio_client)
