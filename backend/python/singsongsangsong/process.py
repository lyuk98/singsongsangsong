# -*- coding: utf-8 -*-

"""
곡 분석 작업 처리
================

Controller에서 받은 곡 분석 요청을 처리합니다
"""

import os
from tempfile import TemporaryDirectory
from itertools import islice
import requests
import librosa
from essentia.standard import MonoLoader # pylint: disable=no-name-in-module
import file_server
import database
import s4dsp

def analyse(song_id: int, audio_path: str):
    """곡 분석을 진행합니다

    Parameters
    ----------
    song_id : int
        곡 ID
    audio_path : str
        MinIO 내 음원 파일 경로

    Raises
    ------
    Exception
        내부 작업 중 오류 발생
    """

    # 분석 작업을 위한 임시 directory 생성
    with TemporaryDirectory() as pwd:
        path: str = pwd.name + "/{}"

        # 음원 파일 다운로드
        os.mkdir(path.format("audio"))
        file_server.download("audio", audio_path, path.format(f"audio/{audio_path}"))

        # 음원 파일 불러오기
        reference_path = path.format(f"audio/{audio_path}")
        y, sr = librosa.load(reference_path, mono=True)
        reference_audio = MonoLoader(filename=reference_path, sampleRate=16000, resampleQuality=4)()

        # 음원 분석 0 - duration
        duration = int(y.shape[0] / sr)

        # 음원 분석 1 - tempo
        tempo = s4dsp.get_tempo(y, sr)

        # 음원 분석 2 - key
        key = s4dsp.find_key(y, sr)[0]

        # 음원 분석 3 - genre
        genres = s4dsp.predict_genre(reference_audio)

        # 음원 분석 4 - mood
        moods = s4dsp.predict_mood(reference_audio)

        # 음원 분석 5 - structure
        structure = [
            {
                "start": time[0],
                "end": time[1],
                "label": label
            } for time, label in islice(s4dsp.find_structure(reference_path))
        ]

        # TODO: 음원 분석 6

        # 음원 분석 완료 - 분석 결과 저장 시도
        saved_files = []
        try:
            with database.get_connection() as connection:
                connection.autocommit = False

                with connection.cursor() as cursor:
                    # 장르 데이터 준비
                    genre_data = []
                    for genre_str, correlation in islice(genres.items(), 5):
                        [genre, subgenre] = genre_str.split("---")

                        # song_id, main_category, sub_category, correlation
                        genre_data.append((song_id, genre, subgenre, correlation))

                    # 분위기 데이터 준비
                    mood_data = [
                        # song_id, atmosphere, correlation
                        (
                            song_id,
                            mood,
                            correlation
                        ) for mood, correlation in islice(moods.items(), 5)
                    ]

                    # 장르, 분위기 삽입
                    cursor.executemany(
                        "insert into genre (song_id, main_category, sub_category, correlation)"
                        "values (%s, %s, %s, %s)",
                        genre_data
                    )
                    cursor.executemany(
                        "insert into atmosphere (song_id, atmosphere, correlation)"
                        "values (%s, %s, %s)",
                        mood_data
                    )

                    # TODO: 파일 데이터 업로드

                    # 나머지 분석 데이터 삽입
                    cursor.execute(
                        "update song"
                        "set duration = %s, bpm = %s, chord = %s"
                        # TODO: add structure, image ID
                        "where id = %s",
                        (
                            duration,
                            tempo,
                            key
                        )
                    )

                    connection.commit()
        except Exception as exception:
            raise exception

        # 작업 완료 - API 서버에 처리 완료 통보
        requests.patch(
            f"{os.environ.get('API_SERVER_URI')}/song/{song_id}",
            timeout=10
        )
