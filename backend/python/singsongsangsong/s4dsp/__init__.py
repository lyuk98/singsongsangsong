"""
싱송생송 음악 특성 분석
===============

곡의 특징 및 유사도를 분석하는 기능을 제공합니다

Utility functions
-----------------
`get_tempo`
    템포를 측정합니다
`find_key`
    조성을 계산합니다
`predict_genre`
    장르를 예측합니다
`predict_mood`
    분위기를 예측합니다
"""

from s4dsp.tempo import get_tempo
from s4dsp.key import find_key
from s4dsp.genre import predict_genre
from s4dsp.mood import predict_mood
