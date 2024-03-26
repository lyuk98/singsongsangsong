package com.ssafy.singsongsangsong.repository.maria.song;

import java.util.List;

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
	public List<Song> findSongByBpmAndKeyword(String keyword, int startBpm, int endBpm, List<OrderSpecifier> orderSpecifier);
	public List<Song> genreFilter(String requestGenre,List<Song> songListFrom);
}
