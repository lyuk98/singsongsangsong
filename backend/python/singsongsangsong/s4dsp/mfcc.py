import librosa
import librosa.display
import matplotlib.pyplot as plt

def plot_mfcc(mfccs, file_name):
    # MFCC 그리기
    plt.figure(figsize=(12, 2))
    librosa.display.specshow(mfccs, x_axis="time", cmap="afmhot")
    plt.colorbar(format="%+2.0f dB")
    plt.title("MFCC")
    plt.xlabel("Time")
    plt.ylabel("MFCC Coefficient")

    # 파일 저장
    plt.savefig(file_name)
    # 파일 확인
    # plt.show()

# 입력값
FILENAME = "s4dsp/data/audio.wav"
SAMPLE_RATE = 22050
DESTINATION = "savefig_default.png"
y, _ = librosa.load(FILENAME, sr=SAMPLE_RATE, mono=True)
mfccs = librosa.feature.mfcc(y=y, sr=SAMPLE_RATE, n_mfcc=20)

plot_mfcc(mfccs, DESTINATION)
