package com.ssafy.singsongsangsong.repository.maria.artist;

import static com.ssafy.singsongsangsong.entity.QFollower_Following.*;
import static com.ssafy.singsongsangsong.entity.QLikes.*;
import static com.ssafy.singsongsangsong.entity.QSong.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.Song;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArtistRepositoryCustomImpl implements ArtistRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public int getLikeCountByArtistId(Long artistId) {
		return jpaQueryFactory.selectFrom(likes).where(likes.artist.id.eq(artistId)).fetch().size();
	}

	@Override
	public int getSongCountByArtistId(Long artistId) {
		return jpaQueryFactory.selectFrom(song).where(song.artist.id.eq(artistId)).fetch().size();
	}

	@Override
	public int getFollowerCountByArtistId(Long artistId) {
		return jpaQueryFactory.selectFrom(follower_Following)
			.where(follower_Following.to.id.eq(artistId))
			.fetch()
			.size();
	}

	@Override
	public List<Song> getPublishedSongsByArtistId(Long artistId) {
		return jpaQueryFactory.selectFrom(song)
			.where(song.artist.id.eq(artistId), song.isPublished.eq(true))
			.fetch()
			.stream()
			.toList();
	}
}
