package com.ssafy.singsongsangsong.repository.maria.song;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.ssafy.singsongsangsong.entity.QAtmosphere.*;
import static com.ssafy.singsongsangsong.entity.QGenre.*;
import static com.ssafy.singsongsangsong.entity.QSong.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.dto.SongBriefDto;
import com.ssafy.singsongsangsong.entity.Atmosphere;
import com.ssafy.singsongsangsong.entity.Genre;
import com.ssafy.singsongsangsong.entity.QGenre;
import com.ssafy.singsongsangsong.entity.Song;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SongRepositoryCustomImpl implements SongRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Song> getPublishedSongsByArtistId(Long artistId) {
		return jpaQueryFactory
			.selectFrom(song)
			.where(song.isPublished.eq(true), song.artist.id.eq(artistId))
			.fetch();
	}

	@Override
	public List<Song> findSongOrderByWeeklyCountDesc() {
		return jpaQueryFactory
			.selectFrom(song)
			.orderBy(song.weeklyPlayCount.desc())
			.limit(10)
			.fetch();
	}

	@Override
	public List<Song> findSongForGenreOrderByWeeklyCountDesc(String requestGenre) {
		return jpaQueryFactory
			.select(song)
			.from(genre)
			.join(genre.song,song)
			.where(genre.mainCategory.eq(requestGenre).or(song.customGenre.eq(requestGenre)))
			.orderBy(song.weeklyPlayCount.desc())
			.limit(10)
			.fetch();
	}

	@Override
	public List<Song> findSongForAtmosphereOrderByWeeklyCountDesc(String requestAtmosphere) {
		return jpaQueryFactory
			.select(song)
			.from(atmosphere1)
			.join(atmosphere1.song,song)
			.where(atmosphere1.atmosphere.eq(requestAtmosphere))
			.orderBy(song.weeklyPlayCount.desc())
			.limit(10)
			.fetch();
	}

	@Override
	public List<Song> findSongByBpmAndKeyword(String keyword, int startBpm, int endBpm,List<OrderSpecifier> orderSpecifiers) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(song.bpm.between(startBpm,endBpm));
		if(keyword != null) {
			builder.and(song.title.contains(keyword));
		}

		return jpaQueryFactory
			.select(song).distinct().from(song)
			.where(builder)
			.fetch();
	}

	@Override
	public List<Song> genreFilter(String requestGenre, List<Song> songListFrom) {
		return null;
	}

}
