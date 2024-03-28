# -*- coding: utf-8 -*-

from typing import Union
import itertools
import os
import shutil
import uuid
import requests
from requests.exceptions import HTTPError
from dotenv import load_dotenv
import pandas as pd
from mysql import connector
from mysql.connector.abstracts import MySQLConnectionAbstract
from mysql.connector.pooling import PooledMySQLConnection
from minio import Minio

def download(url: str, path: str):
    """URL로부터 지정한 파일을 `path`에 저장합니다

    Parameters
    ----------
    url : str
        URL
    path : str
        저장할 파일의 경로
    """

    request = requests.get(url, stream=True, timeout=5)
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

def get_database_connection() -> Union[PooledMySQLConnection, MySQLConnectionAbstract]:
    """MySQL (MariaDB)에 연결합니다

    Returns
    -------
    PooledMySQLConnection | MySQLConnectionAbstract
        데이터베이스 연결 객체
    """

    return connector.connect(
        host=os.environ.get("MYSQL_HOST"),
        port=os.environ.get("MYSQL_PORT"),
        user=os.environ.get("MYSQL_USER"),
        password=os.environ.get("MYSQL_PASSWORD"),
        database=os.environ.get("MYSQL_DATABASE")
    )

def get_minio_client() -> Minio:
    """MinIO client를 생성합니다
    
    Returns
    -------
    Minio
        MinIO client
    """
    return Minio(
        os.environ.get("MINIO_ENDPOINT"),
        access_key=os.environ.get("MINIO_ACCESS_KEY"),
        secret_key=os.environ.get("MINIO_SECRET_KEY"),
        secure=False
    )

# .env를 읽습니다
load_dotenv()

# 아티스트의 정보를 읽습니다
artists = pd.read_csv(
    "dataset/fma/data/fma_metadata/raw_artists.csv",
    skipinitialspace=True,
    usecols=[
        "artist_id",
        "artist_bio",
        "artist_handle",
        "artist_image_file",
        "artist_name"
    ]
)

insert = []
file_insert = []
insert_id = itertools.count(1)

for index, row in artists.iterrows():
    # 아티스트 이미지를 다운로드하기 전 URL을 정리합니다
    download_url = row["artist_image_file"]

    filename = download_url[
        len("https://freemusicarchive.org/file/images/artists/"):
    ] if download_url.startswith(
        "https://freemusicarchive.org/file/images/artists/"
    ) else None
    download_url = update_image_url(
        download_url
    ) if download_url.startswith(
        "https://freemusicarchive.org/file/images/artists/"
    ) else None

    # 유효한 이미지가 주어진 경우 다운로드를 진행합니다
    if filename is not None:
        download_path = os.path.join("dataset", "images", "artists", filename)

        try:
            # 파일이 존재하지 않을 시에만 진행합니다
            if not os.path.exists(download_path) and download_url is not None:
                print(f"Downloading from {download_url}")
                download(download_url, download_path)
            else:
                print(f"Using downloaded {filename}")

            # file entity에 삽입할 데이터를 준비합니다
            file_id = next(insert_id)
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
    else:
        file_id = None # pylint: disable=invalid-name

    # 삽입할 데이터를 준비합니다
    insert.append(
        {
            "id": row["artist_id"],
            "age": 20,
            "sex": "F",
            "profile_image_id": file_id,
            "introduction": row["artist_bio"],
            "nickname": row["artist_name"],
            "username": row["artist_handle"],
            "role": "GUEST"
        }
    )
