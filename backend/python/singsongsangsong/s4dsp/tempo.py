import librosa

filename = "data/audio.wav"

y, sr = librosa.load(filename)
tempo, beat_frames = librosa.beat.beat_track(y = y, sr = sr)

print("Tempo: {:.2f}".format(tempo))
