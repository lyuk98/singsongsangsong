package com.ssafy.singsongsangsong.repository.maria.song;

import java.util.List;
import java.util.Optional;

import com.ssafy.singsongsangsong.entity.Song;

public interface SongRepositoryCustom {
	public List<Song> getPublishedSongsByArtistId(Long artistId);

	public Optional<Song> getSongByArtistIdAndSongId(Long songId, Long artistId);

	void decrementEmotionCount(Long songId, Long artistId, String emotionName) throws NoSuchFieldException;

	void incrementEmotionCount(Long songId, Long artistId, String emotionName);
}
