# -*- coding: utf-8 -*-

"""
구조 분석
========

`s4dsp.structure`는 음원에 대한 구조를 분할하고 각 구조에 대한 특성을 인식합니다

Utility functions
-----------------
`find_structure`
    구조를 분할하여 특성을 인식합니다
"""

import numpy as np
import scipy.signal
import librosa
from pychorus import create_chroma
from pychorus.similarity_matrix import TimeTimeSimilarityMatrix, TimeLagSimilarityMatrix, Line
import msaf

# 하나의 chunk에서 고려할 샘플 수
_N_FFT = 2**7

# Denoising size (초 단위)
_SMOOTHING_SIZE_SECONDS = 2.5

# Line 겹침 확인 시 허용하는 오차
_LINE_OVERLAP_MARGIN = 0.2

def local_maxima_rows(time_lag_similarity):
    """국부 최댓값인 열 확인"""
    row_sums = np.sum(time_lag_similarity, axis=1)
    divisor = np.arange(row_sums.shape[0], 0, -1)

    normalized_rows = row_sums / divisor

    local_minima_rows = scipy.signal.argrelextrema(normalized_rows, np.greater)
    return local_minima_rows[0]

def detect_lines_helper(time_lag_matrix, rows, threshold, min_length_samples):
    """min_length_samples가 threshold 이상인 line 인식"""
    num_samples = time_lag_matrix.shape[0]
    line_segments = []
    segment_start = None
    for row in rows:
        if row < min_length_samples:
            continue
        for col in range(row, num_samples):
            if time_lag_matrix[row, col] > threshold:
                if segment_start is None:
                    segment_start = col
            else:
                if (
                    segment_start is not None
                ) and (col - segment_start) > min_length_samples:
                    line_segments.append(Line(segment_start, col, row))
                segment_start = None
    return line_segments

def detect_lines(time_lag_similarity, rows, min_length_samples):
    """Time lag matrix에서 line 인식"""
    line_threshold = 0.15
    minimum_lines = 10
    iterations = 20

    threshold = line_threshold
    for throwaway in range(iterations): # pylint: disable=unused-variable
        line_segments = detect_lines_helper(
            time_lag_similarity,
            rows,
            threshold,
            min_length_samples
        )
        if len(line_segments) >= minimum_lines:
            return line_segments
        threshold *= 0.95

    return line_segments

def count_overlapping_lines(lines, margin, min_length_samples):
    """모든 line 쌍을 확인하여 겹치는지 확인"""
    line_scores = {}
    for line in lines:
        line_scores[line] = 0

    # Line 쌍 확인
    for line_1 in lines:
        for line_2 in lines:
            lines_overlap_vertically = (
                line_2.start < (line_1.start + margin)
            ) and (
                line_2.end > (line_1.end - margin)
            ) and (
                abs(line_2.lag - line_1.lag) > min_length_samples
            )

            lines_overlap_diagonally = (
                (line_2.start - line_2.lag) < (line_1.start - line_1.lag + margin)
            ) and (
                (line_2.end - line_2.lag) > (line_1.end - line_1.lag - margin)
            ) and (
                abs(line_2.lag - line_1.lag) > min_length_samples
            )

            if lines_overlap_vertically or lines_overlap_diagonally:
                line_scores[line_1] += 1

    return line_scores

def sort_segments(line_scores):
    """Line을 chorus 및 길이로 정렬"""
    to_sort = [(line, line_scores[line], line.end - line.start) for line in line_scores]
    to_sort.sort(key=lambda x: (x[1], x[2]), reverse=True)

    return to_sort

def find_structure(path: str, sr: float): # pylint: disable=too-many-locals
    """구조를 분할하여 특성을 인식합니다"""
    # Power spectrogram을 활용하여 chromagram 생성
    chromagram, y, sr, duration = create_chroma(path, _N_FFT)

    # MSAF를 활용하여 구간 분할
    processed_boundaries, processed_labels = msaf.process(
        path,
        feature="mfcc",
        boundaries_id="foote",
        labels_id="fmc2d",
        out_sr=sr
    )

    boundaries = []
    labels = []
    mfccs = []
    for i in range(len(processed_boundaries) - 1):
        # 5초보다 긴 구간들을 선택하여 MFCC 계산
        if processed_boundaries[i + 1] - processed_boundaries[i] >= 5:
            segment = y[
                librosa.time_to_samples(processed_boundaries[i], sr=sr):
                librosa.time_to_samples(processed_boundaries[i + 1], sr=sr)
            ]
            mfcc = librosa.feature.mfcc(y=segment, sr=sr)

            boundaries.append(processed_boundaries[i])
            labels.append(processed_labels[i])
            mfccs.append(np.average(mfcc, axis=0))

    num_samples = chromagram.shape[1]

    # Time time similarity matrix, time lag similarity matrix 생성
    time_time_similarity = TimeTimeSimilarityMatrix(chromagram, sr)
    time_lag_similarity = TimeLagSimilarityMatrix(chromagram, sr)

    chromagram_sr = num_samples / duration
    clip_length = 10
    smoothing_size_samples = int(_SMOOTHING_SIZE_SECONDS * chromagram_sr)

    # Time lag similarity matrix에 대한 denoising 진행
    time_lag_similarity.denoise(time_time_similarity.matrix, smoothing_size_samples)

    clip_length_samples = clip_length * chromagram_sr

    candidate_rows = local_maxima_rows(time_lag_similarity.matrix)

    # Time lag similarity matrix에서 line 인식
    lines = detect_lines(time_lag_similarity.matrix, candidate_rows,
                        clip_length_samples)

    assert len(lines) != 0

    # 겹치는 line 정렬
    line_scores = count_overlapping_lines(
        lines, _LINE_OVERLAP_MARGIN * clip_length_samples,
        clip_length_samples)

    choruses = sort_segments(line_scores)

    # 구간의 시작과 종점 계산
    chorus_times = [
        (chorus[0].start / chromagram_sr, chorus[0].end / chromagram_sr) for chorus in choruses
    ]

    # 구간 시간별 정렬
    chorus_times.sort(key=lambda x: x[0])

    sorted_chorus_times = [chorus_times[0]]
    # 겹치는 구간 제거
    for i in range(1, len(chorus_times)):
        if (chorus_times[i][0] - sorted_chorus_times[-1][0]) >= clip_length:
            sorted_chorus_times.append(chorus_times[i])

    max_onset = 0
    best_chorus = []

    # 10초와 30초 사이 길이의 chorus 후보를 지정한 뒤 onset 인식으로 best chorus 지정
    for time in sorted_chorus_times:
        if 10 <= (time[1] - time[0]) and (time[1] - time[0]) <= 30:
            y_chorus = y[int(time[0] * sr) : int(time[1] * sr)]
            onset_detect = librosa.onset.onset_detect(y=y_chorus, sr=sr)
            if np.mean(onset_detect) >= max_onset:
                max_onset = np.mean(onset_detect)
                best_chorus = y_chorus

    # Best chorus의 MFCC 계산
    chorus_mfcc = np.average(librosa.feature.mfcc(y=best_chorus, sr=sr), axis=0)

    structure_labels = [""] * len(labels)

    #calculate the dtw similarity between each segment and the detected chorus segment
    #also detect the minimum and maximum distance values
    max_dist = 0
    min_dist = 100
    similarity_measures = []
    euclidean_norm = lambda x, y: np.abs(x - y)
    for x in range(len(new_boundaries)):
        dist = fastdtw(mfccs[x], chorus_mfcc, dist=euclidean_norm)
        similarity_measures.append(dist)
        if dist > max_dist:
            max_dist = dist
        if dist < min_dist:
            min_dist = dist

    #normalize the similarity measures and sort
    normalized = [float(i)/max(similarity_measures) for i in similarity_measures]
    sorted_norms = sorted(normalized)

    #normalize the threshold; songs with larger ranges, take a lower threshold value,
    #whereas for songs for a higher range, take a higher threshold
    bottom = []
    if max_dist - min_dist <= 2:
        bottom = sorted_norms[int(len(sorted_norms) * 0) : int(len(sorted_norms) * .5)]
    else:
        bottom = sorted_norms[int(len(sorted_norms) * 0) : int(len(sorted_norms) * .40)]

    #if the calculated dtw similarity value for a segment is below the normalized threshold,
    #that segment is labeled the chorus
    for x in range(len(structure_labels)):
        if normalized[x] <= bottom[-1]:
            structure_labels[x] = "chorus"

    #label the other segments -- repeating non chorus segments are considered verses,
    #transitions are unique segments that appear in the middle of a song,
    #and intros and outros are unique segments that appear at the beginning and ending
    #of a song respectively
    for x in range(len(structure_labels)):
        found_match = False
        for y in range(x + 1, len(structure_labels)):
            if (new_labels[x] == new_labels[y]) and structure_labels[y] == ""  and structure_labels[x] == "":
                found_match = True
                structure_labels[x] = "verse"
                structure_labels[y] = "verse"
        if found_match == False and structure_labels[x] == "":
            if x == 0:
                structure_labels[x] = "intro"
            elif x == (len(new_boundaries) - 1):
                structure_labels[x] = "outro"
            else:
                structure_labels[x] = "transition"


    #write the labels to a text file, to be used in Audacity
    frames = open("labels/" + file_name + "_labels.txt", "w")
    for e in range(len(new_boundaries)):
        if e < len(new_boundaries) - 1:
            outer_bound = e+1
            frames.write(str(round(new_boundaries[e])) + "\t" + str(round(new_boundaries[outer_bound])) + "\t" + structure_labels[e] + "\n")
        else:
            frames.write(str(round(new_boundaries[e])) + "\t" + str(round(song_length_sec)) + "\t" + structure_labels[e] + "\n")

if __name__ == "__main__":
    FILENAME = "s4dsp/data/audio.wav"
