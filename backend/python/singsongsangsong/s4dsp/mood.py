import requests
import os.path
import json
from essentia.standard import MonoLoader, TensorflowPredictEffnetDiscogs, TensorflowPredict2D
import numpy as np
from itertools import islice

# 곡 분위기 분석에 필요한 파일 정보
file_infos = [
    {
        "basepath": "https://essentia.upf.edu/models/music-style-classification/discogs-effnet/",
        "filename": "discogs-effnet-bs64-1.pb"
    },
    {
        "basepath": "https://essentia.upf.edu/models/classification-heads/mtg_jamendo_moodtheme/",
        "filename": "mtg_jamendo_moodtheme-discogs-effnet-1.pb"
    },
    {
        "basepath": "https://essentia.upf.edu/models/classification-heads/mtg_jamendo_moodtheme/",
        "filename": "mtg_jamendo_moodtheme-discogs-effnet-1.json"
    }
]

# 필요한 파일 존재 확인 및 없을 시 다운로드
for file_info in file_infos:
    filepath = "data/{}".format(file_info.get("filename"))
    
    if not os.path.isfile(filepath):
        print("Downloading {}".format(file_info.get("filename")))
        response = requests.get(file_info.get("basepath") + file_info.get("filename"))

        file = open(filepath, "wb")
        file.write(response.content)
        file.close()
    else:
        print("Using downloaded {}".format(file_info.get("filename")))

# Metadata 파일에서 분위기 이름 불러오기
metadata_file = open("data/{}".format("mtg_jamendo_moodtheme-discogs-effnet-1.json"), encoding="utf-8")
metadata = json.load(metadata_file)
metadata_file.close()
mood_names = metadata.get("classes")

filename = "data/audio.wav"

# 음원 파일을 16kHz 모노 형식으로 불러온 후 EffnetDiscogs 기반 모델로 embeddings 생성
audio = MonoLoader(filename=filename, sampleRate=16000, resampleQuality=4)()
embedding_model = TensorflowPredictEffnetDiscogs(graphFilename="data/discogs-effnet-bs64-1.pb", output="PartitionedCall:1")
embeddings = embedding_model(audio)

# 다운로드 받은 모델로 분위기 예측 및 평균값 계산
model = TensorflowPredict2D(graphFilename="data/mtg_jamendo_moodtheme-discogs-effnet-1.pb", output="model/Sigmoid")
predictions = model(embeddings)
predictions_mean = np.mean(predictions, axis=0)

# 높은 순으로 분위기 정렬
moods = { mood_names[i]: predictions_mean[i] for i in range(len(mood_names)) }
moods = dict(sorted(moods.items(), key=lambda item: item[1], reverse=True))

# 상위 5개의 분위기 출력
print("Mood classification result")
for mood, value in islice(moods.items(), 5):
    print("  {}: {:.2f}".format(mood, value))
