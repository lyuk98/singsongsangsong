package com.ssafy.singsongsangsong.repository.maria.song;

public interface GenreRepositoryCustom {
	boolean correlationGenreCheck(String genre, Long songId);
}
