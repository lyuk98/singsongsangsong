import os
from voyager import Index, Space
import numpy as np
import librosa
import time
import sys

def get_mfcc(y: np.ndarray, sr: float) -> np.ndarray:
	mfcc = librosa.feature.mfcc(y=y, sr=sr)

	mean = np.mean(mfcc, axis=0)
	covariance = np.cov(mfcc, rowvar=False)
	
	vector = np.concatenate([np.diag(covariance, k) for k in range(len(mean))])
	vector = np.concatenate((mean, vector))
	print(vector.shape)

	# vector = mean
	# for k in range(len(mean)):
	# 	diagonal = np.diag(covariance, k)
	# 	print(diagonal)
	# 	print(len(diagonal))
	# 	vector = np.concatenate((vector, diagonal))
	
	return vector

def add_audio(reference: list[float], index: Index):
	return index.add_item(reference[:index.num_dimensions])

# Parameters
reference_path = "data/train/"
compare_path = "data/compare.wav"
index_path = "data/index.voy"

sample_rate = 22050
num_dimensions = 12582912

# Create index
index = Index(Space.Euclidean, num_dimensions=num_dimensions)

references = {}

with os.scandir(reference_path) as it:
	for entry in it:
		if entry.name.startswith(".") or not entry.is_file():
			continue

		audio, _ = librosa.load(entry.path, sr=sample_rate, mono=True)
		mfcc = get_mfcc(y=audio, sr=sample_rate)
		print(mfcc)
		print(len(mfcc))

		print(f"Adding {entry.name} to index")

		add_start = time.perf_counter_ns()
		audio_id = add_audio(mfcc, index)
		add_end = time.perf_counter_ns()

		print(f"Adding {entry.name} took {(add_end - add_start) // 1000000} milliseconds")

		references[audio_id] = entry.name
		# sys.exit()

audio, _ = librosa.load(compare_path, sr=sample_rate, mono=True)
compare_mfcc = get_mfcc(y=audio, sr=sample_rate)
del audio

print(f"Querying {compare_path}")

query_start = time.perf_counter_ns()
neighbours, distances = index.query(compare_mfcc[:num_dimensions], k=3)
query_end = time.perf_counter_ns()

print(f"Querying took {(query_end - query_start) // 1000000} milliseconds")

print("Result:")
for i in range(len(neighbours)):
	print(f"  {references[neighbours[i]]}: {distances[i]}")
