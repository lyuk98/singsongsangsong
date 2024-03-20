package com.ssafy.singsongsangsong.repository.maria.song;

import static com.ssafy.singsongsangsong.entity.QSong.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
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

}
