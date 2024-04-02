package com.ssafy.singsongsangsong.repository.maria.liked;

import static com.ssafy.singsongsangsong.entity.QLikes.*;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.Likes;
import com.ssafy.singsongsangsong.entity.Song;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LikedRepositoryCustomImpl implements LikedRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<Likes> getLikedSongsByArtistId(Long artistId) {
		return jpaQueryFactory
			.selectFrom(likes)
			.where(likes.artist.id.eq(artistId))
			.orderBy(likes.id.desc())
			.fetch();
	}
}
