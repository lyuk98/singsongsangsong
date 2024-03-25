"""
싱송생송 DSP API
===============
"""

from typing import Annotated
from fastapi import FastAPI, Path, Query, Response, status
import uvicorn

app = FastAPI()

@app.post("/song")
def request_song_analysis(
    song_id: Annotated[int, Query(alias="id")],
    audio_path: Annotated[str, Query(alias="path")]
):
    return Response(status_code=status.HTTP_501_NOT_IMPLEMENTED)

@app.get("/song/{id}")
def query_progress(
    song_id: Annotated[int, Path(alias="id")]
):
    return Response(status_code=status.HTTP_501_NOT_IMPLEMENTED)

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
