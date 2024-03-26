# -*- coding: utf-8 -*-

"""
싱송생송 DSP API
===============
"""

from typing import Annotated
from fastapi import (
    BackgroundTasks,
    FastAPI,
    Query,
    Response,
    status
)
from minio.error import S3Error
import uvicorn
import file_server
from process import analyse

app = FastAPI(
    title="싱송생송 DSP API"
)

@app.post(
    "/song",
    summary="곡 분석 요청",
    response_model=None,
    responses={
        202: { "description": "요청 확인, 분석 진행 예정" },
        404: { "description": "올바르지 않은 `path` 제공" },
        422: { "description": "올바르지 않은 형태의 `id` 제공" },
        500: { "description": "예상치 못한 오류 발생" }
    },
    status_code=202
)
def request_song_analysis(
    song_id: Annotated[
        int,
        Query(
            alias="id",
            description="분석할 곡 ID"
        )
    ],
    audio_path: Annotated[
        str,
        Query(
            alias="path",
            description="음원 파일 경로"
        )
    ],
    background_tasks: BackgroundTasks
):
    """
    곡 분석 요청
    -----------

    지정한 `id`를 가진 곡에 대한 분석을 진행합니다

    분석 완료 후 데이터베이스에 분석 결과를 저장한 뒤 API 서버의 callback endpoint에 분석 완료를 알립니다

    Parameters
    ----------
    song_id : int
        분석할 곡 ID
    audio_path : str
        음원 파일 경로
    """

    # 음원이 파일 서버에 존재하는지 확인
    client = file_server.get_client()
    try:
        client.stat_object("audio", audio_path)
    except S3Error:
        # 존재하지 않을 시 HTTP 404 응답
        return Response(status_code=status.HTTP_404_NOT_FOUND)

    background_tasks.add_task(analyse, song_id, audio_path)
    return Response(status_code=status.HTTP_202_ACCEPTED)

@app.post("/similarity")
def save_similarity_data(
    song_id: Annotated[int, Query(alias="id")],
    audio_path: Annotated[str, Query(alias="path")]
):
    return Response(status_code=status.HTTP_501_NOT_IMPLEMENTED)

@app.get("/similarity")
def check_similarity(
    audio_path: Annotated[str, Query(alias="path")]
):
    return Response(status_code=status.HTTP_501_NOT_IMPLEMENTED)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
