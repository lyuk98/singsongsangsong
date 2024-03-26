# -*- coding: utf-8 -*-

"""
데이터베이스 관리
================

MySQL (MariaDB) 데이터베이스와 관련된 기능을 제공합니다
"""

import os
from typing import Union
from dotenv import load_dotenv
from mysql import connector
from mysql.connector.abstracts import MySQLConnectionAbstract
from mysql.connector.pooling import PooledMySQLConnection
from mysql.connector.cursor import MySQLCursorAbstract

load_dotenv()

def get_connection() -> Union[PooledMySQLConnection, MySQLConnectionAbstract]:
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

def get_song_path(song_id: int, cursor: MySQLCursorAbstract=None) -> Union[str, None]:
    """지정한 `song_id`의 ID를 가진 곡의 음원 파일 경로를 반환합니다

    Returns
    -------
    str | None
        음원 파일 경로; 없을 시 `None`
    """

    if cursor is None:
        connection = get_connection()
        cursor = connection.cursor()

    cursor.execute(
        "select id, music_location from song where id = %s",
        (song_id,)
    )

    for (selected_id, music_location) in cursor:
        if song_id == selected_id:
            return music_location

    return None
