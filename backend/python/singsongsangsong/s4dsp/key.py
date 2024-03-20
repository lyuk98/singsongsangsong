import numpy as np
import librosa
from numbers import Number

def find_key(waveform: np.ndarray, sr: float, time_start: Number=None, time_end: Number=None):
	"""특정 음악의 waveform에서 조성을 계산합니다

	Krumhansl-Schmuckler key-finding algorithm을 사용하여 조성을 계산합니다

	파라미터
	--------
	waveform : np.ndarray
		Waveform 데이터
	sr : float
		Sampling rate
	time_start : Number
		시작점 (초 단위)
	time_end : Number
		끝 (초 단위)
	
	반환 값
	-------
	key : string
		계산된 조성
	correlation : float
		계산된 조성의 연관성
	alternative_key : string | None
		``key``의 ``correlation``에 근접한 조성이 있을 경우의 조성
	alternative_correlation : float | None
		``alternative_key``의 연관성
	"""

	# 시작 또는 마무리 타임스탬프가 지정되어 있는 경우 적절한 형태로 변환
	if time_start is not None:
		time_start = librosa.time_to_samples(times=time_start, sr=sr)
	if time_end is not None:
		time_end = librosa.time_to_samples(times=time_end, sr=sr)
	
	# Waveform에서 chromagram 생성
	y_segment = waveform[time_start:time_end]
	chromagram = librosa.feature.chroma_cqt(y=y_segment, sr=sr, bins_per_octave=24)

	# Chromagram에서 각각의 pitch(C, D#, ...)가 존재하는 정도 기록
	chroma_values = []
	for i in range(12):
		chroma_values.append(np.sum(chromagram[i]))
	
	# Key의 이름을 chroma_values의 값과 연결
	pitches = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
	key_frequencies = { pitches[i]: chroma_values[i] for i in range(12) }

	# 가능한 결과 24개의 목록
	keys = [pitches[i] + " major" for i in range(12)] + [pitches[i] + " minor" for i in range(12)]

	# Krumhansl-Schmuckler key-finding algorithm을 사용할 때 쓰이는 프로필
	# 장조와 단조가 보편적으로 가지는 chroma_values의 값
	major_profile = [6.35, 2.23, 3.48, 2.33, 4.38, 4.09, 2.52, 5.19, 2.39, 3.66, 2.29, 2.88]
	minor_profile = [6.33, 2.68, 3.52, 5.38, 2.60, 3.53, 2.54, 4.75, 3.98, 2.69, 3.34, 3.17]

	# 12개의 pitch에서 각각 시작하여 프로필과의 연관성 계산
	major_key_correlations = []
	minor_key_correlations = []
	for i in range(12):
		key_test = [key_frequencies.get(pitches[(i + j) % 12]) for j in range(12)]
		major_key_correlations.append(round(np.corrcoef(major_profile, key_test)[1, 0], 3))
		minor_key_correlations.append(round(np.corrcoef(minor_profile, key_test)[1, 0], 3))
	
	# 모든 장조 및 단조와 연관성 저장
	key_dictionary = {
		**{ keys[i]: major_key_correlations[i] for i in range(12) },
		**{ keys[i + 12]: minor_key_correlations[i] for i in range(12) }
	}

	# 연관성 데이터에서 최대값 (알고리즘이 인지한 곡의 조성) 저장
	key = max(key_dictionary, key=key_dictionary.get)
	correlation = max(key_dictionary.values())

	# 연관성 데이터에서 최대값과 근사한 값이 존재하는 경우 저장
	alternative_key = None
	alternative_correlation = None

	# 연관성이 최대값의 90% 이상인 경우 대체 조성 저장
	for test_key, test_correlation in key_dictionary.items():
		if test_correlation > correlation * 0.9 and test_correlation != correlation:
			alternative_key = test_key
			alternative_correlation = test_correlation
	
	return key, correlation, alternative_key, alternative_correlation

filename = "data/audio.wav"

y, sr = librosa.load(filename)
y_harmonic, y_percussive = librosa.effects.hpss(y)

key, correlation, alternative_key, alternative_correlation = find_key(y_harmonic, sr)

print("Key: " + key)
print("Correlation: {:.2f}".format(correlation))

if alternative_key is not None and alternative_correlation is not None:
	print("")
	print("Alternative key: " + alternative_key)
	print("Alternative correlation: {:.2f}".format(alternative_correlation))
