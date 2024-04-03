package com.ssafy.singsongsangsong.repository.maria.song;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.singsongsangsong.entity.Genre;
import com.ssafy.singsongsangsong.entity.Song;

public interface SongRepositoryCustom {
	public List<Song> getPublishedSongsByArtistId(Long artistId);
	public List<Song> findSongOrderByWeeklyCountDesc();
	public List<Song> findSongForGenreOrderByWeeklyCountDesc(String requestGenre);
	public List<Song> findSongForAtmosphereOrderByWeeklyCountDesc(String requestAtmosphere);
	public List<Song> findSongByBpmAndKeyword(String keyword, int startBpm, int endBpm, OrderSpecifier[] orderSpecifier, String requestGenre, String requestAtmosphere);
	public Optional<Song> getSongByArtistIdAndSongId(Long songId, Long artistId);

	void decrementEmotionCount(Long songId, Long artistId, String emotionName) throws NoSuchFieldException;

	void incrementEmotionCount(Long songId, Long artistId, String emotionName);

	List<Song> findByThemeName(String themeName, int size);

	void incrementPlayCount(Long songId);

	void incrementDownloadCount(Long songId);
}
