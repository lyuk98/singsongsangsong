package com.ssafy.singsongsangsong.repository.maria.genre;

import java.util.List;

import com.ssafy.singsongsangsong.entity.Genre;

public interface GenreRepositoryCustom {

	List<Genre> findBySongId(Long songId, int limit);
}
