package com.ssafy.singsongsangsong.repository;

import java.util.List;

import com.ssafy.singsongsangsong.entity.Genre;

public interface GenreRepositoryCustom {

	List<Genre> findBySongId(Long songId, int limit);
}
