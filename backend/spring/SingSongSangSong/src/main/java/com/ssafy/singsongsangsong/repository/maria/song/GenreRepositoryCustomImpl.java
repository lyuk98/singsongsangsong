package com.ssafy.singsongsangsong.repository.maria.song;

import static com.ssafy.singsongsangsong.entity.QGenre.*;

import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public boolean correlationGenreCheck(String requestGenre, Long songId) {
		return jpaQueryFactory
			.from(genre)
			.where(genre.song.id.eq(songId).and(genre.mainCategory.eq(requestGenre).and(genre.correlation.goe(60))))
			.select(genre.id)
			.fetchFirst() != null;
	}
}
