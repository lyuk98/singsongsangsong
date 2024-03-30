package com.ssafy.singsongsangsong.repository;

import static com.ssafy.singsongsangsong.entity.QGenre.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.Genre;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Genre> findBySongId(Long songId, int limit) {
		return jpaQueryFactory.selectFrom(genre)
			.where(genre.song.id.eq(songId))
			.limit(limit)
			.fetch();
	}
}
