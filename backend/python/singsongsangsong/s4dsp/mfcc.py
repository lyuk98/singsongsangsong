import librosa
import librosa.display
import matplotlib.pyplot as plt
 
def plot_mfcc(mfccs, file_name):
    # MFCC 그리기
    plt.figure(figsize=(12, 2))
    librosa.display.specshow(mfccs, x_axis='time', cmap='afmhot')
    plt.colorbar(format='%+2.0f dB')
    plt.title('MFCC')
    plt.xlabel('Time')
    plt.ylabel('MFCC Coefficient')

    # 파일 저장
    plt.savefig(file_name)
    # 파일 확인
    # plt.show()
 
# 입력값
audio_file_path = "s4dsp/data/audio.wav"
sample_rate = 22050
file_name = "savefig_default.png"
y, _ = librosa.load(audio_file_path, sr=sample_rate, mono=True)
mfccs = librosa.feature.mfcc(y=y, sr=sample_rate, n_mfcc=20)

plot_mfcc(mfccs, file_name)
