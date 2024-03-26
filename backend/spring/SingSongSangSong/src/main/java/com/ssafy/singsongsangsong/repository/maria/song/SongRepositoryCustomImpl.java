package com.ssafy.singsongsangsong.repository.maria.song;

import static com.ssafy.singsongsangsong.entity.QSong.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.Song;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SongRepositoryCustomImpl implements SongRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	private String getEmotionColumnName(String emotionType) {
		return emotionType.toLowerCase() + "EmotionCount";
	}

	@Override
	public List<Song> getPublishedSongsByArtistId(Long artistId) {
		return jpaQueryFactory
			.selectFrom(song)
			.where(song.isPublished.eq(true), song.artist.id.eq(artistId))
			.fetch();
	}

	@Override
	public Optional<Song> getSongByArtistIdAndSongId(Long songId, Long artistId) {
		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(song)
				.where(song.id.eq(songId), song.artist.id.eq(artistId))
				.fetchOne()
		);
	}

	@Override
	public void decrementEmotionCount(Long songId, Long artistId, String emotionName) throws NoSuchFieldException {
		String columnName = getEmotionColumnName(emotionName);
		Path<Integer> targetEmotionPath = Expressions.numberPath(Integer.class, columnName);

		jpaQueryFactory.update(song)
			.set(targetEmotionPath, ((NumberExpression<Integer>)targetEmotionPath).subtract(1))
			.where(song.id.eq(songId).and(song.artist.id.eq(artistId)))
			.execute();
	}

	@Override
	public void incrementEmotionCount(Long songId, Long artistId, String emotionName) {
		String columnName = getEmotionColumnName(emotionName);
		Path<Integer> targetEmotionPath = Expressions.numberPath(Integer.class, columnName);

		jpaQueryFactory.update(song)
			.set(targetEmotionPath, ((NumberExpression<Integer>)targetEmotionPath).add(1))
			.where(song.id.eq(songId).and(song.artist.id.eq(artistId)))
			.execute();
	}
}
