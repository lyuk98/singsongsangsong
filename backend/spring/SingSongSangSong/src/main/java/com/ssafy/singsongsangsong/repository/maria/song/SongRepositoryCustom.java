package com.ssafy.singsongsangsong.repository.maria.song;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.singsongsangsong.entity.Genre;
import com.ssafy.singsongsangsong.entity.Song;

public interface SongRepositoryCustom {
	public List<Song> getPublishedSongsByArtistId(Long artistId);
	public List<Song> findSongOrderByWeeklyCountDesc();
	public List<Song> findSongForGenreOrderByWeeklyCountDesc(String requestGenre);
	public List<Song> findSongForAtmosphereOrderByWeeklyCountDesc(String requestAtmosphere);
	public List<Song> findSongBySearchParam(String keyword,String genre,String atmosphere, int bpm, String sort);
}
