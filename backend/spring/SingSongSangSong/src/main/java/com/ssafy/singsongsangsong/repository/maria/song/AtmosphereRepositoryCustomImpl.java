package com.ssafy.singsongsangsong.repository.maria.song;

import static com.ssafy.singsongsangsong.entity.QAtmosphere.*;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AtmosphereRepositoryCustomImpl implements AtmosphereRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public boolean correlationAtmosphereCheck(String requestAtmosphere, Long songId) {
		return jpaQueryFactory
			.from(atmosphere1)
			.where(atmosphere1.song.id.eq(songId).and(atmosphere1.atmosphere.eq(requestAtmosphere).and(atmosphere1.correlation.goe(60))))
			.select(atmosphere1.id)
			.fetchFirst() != null;
	}
}
