package com.ssafy.singsongsangsong.repository.maria.song;

import static com.ssafy.singsongsangsong.entity.QGenre.*;
import static com.ssafy.singsongsangsong.entity.QSong.*;
import static com.ssafy.singsongsangsong.entity.QArtist.*;
import static com.ssafy.singsongsangsong.entity.QAtmosphere.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Atmosphere;
import com.ssafy.singsongsangsong.entity.Genre;
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
	public List<Song> findSongBySearchParam(String requestKeyword, String requestGenre, String requestAtmosphere, int requestBpm, String sort) {
		List<Song> songResult = new ArrayList<>();
		int startBpm = 0;
		int endBpm = 40;

		if(requestBpm != 0) {
			startBpm = requestBpm;
			endBpm = requestBpm + 20;
		}
		if(requestKeyword != null) {
			songResult = jpaQueryFactory
				.selectFrom(song)
				.where(song.title.contains(requestKeyword).and(song.bpm.between(startBpm,endBpm)))
				.fetch();
		}
		if(requestGenre != null) {
			List<Song> songListByGenre = jpaQueryFactory
				.select(genre.song)
				.from(genre)
				.where(genre.mainCategory.eq(requestGenre).and(genre.correlation.goe(60)))
				.fetch();
			songResult.retainAll(songListByGenre);
			for(Song song : songResult) {
				if(!song.getCustomGenre().equals(requestGenre)) {
					songListByGenre.remove(song);
				}
			}
		}
		if(requestAtmosphere != null) {
			List<Song> songListByAtmosphere = jpaQueryFactory
				.select(atmosphere1.song)
				.from(atmosphere1)
				.where(atmosphere1.atmosphere.eq(requestAtmosphere))
				.fetch();
			songResult.retainAll(songListByAtmosphere);
		}
		return songResult;
	}

}
