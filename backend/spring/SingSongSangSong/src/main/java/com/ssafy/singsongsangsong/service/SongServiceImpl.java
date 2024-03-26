package com.ssafy.singsongsangsong.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.entity.Emotions;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.repository.maria.song.EmotionRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

	private final SongRepository songRepository;

	private final EmotionRepository emotionRepository;

	@Override
	@Transactional
	public void updateEmotionType(Long artistId, Long songId, EmotionsConstants emotionType) throws
		NoSuchFieldException {
		// 기존 사용자가 해당 노래에 대해서 emotion을 남긴 경우, 해당 노래의 emotion을 삭제하고 count down 시킨 다음,
		// 새로운 감정을 추가한다. count up++

		Song song = songRepository.getSongByArtistIdAndSongId(songId, artistId)
			.orElseThrow(() -> new IllegalArgumentException("해당 노래가 존재하지 않습니다."));
		Optional<java.lang.String> targetEmotion = emotionRepository.checkIfEmotionExists(song.getId(), artistId);
		if (targetEmotion.isPresent()) {
			java.lang.String previousEmotionName = targetEmotion.get();
			// 기존 emotion 업데이트 및 반정규화된 table, count 조정
			emotionRepository.updateEmotionType(song.getId(), artistId, emotionType);
			songRepository.decrementEmotionCount(song.getId(), artistId, previousEmotionName);
			songRepository.incrementEmotionCount(song.getId(), artistId, emotionType.getName());
		} else {
			// emotion 추가 및 반정규화된 table, count++
			emotionRepository.save(Emotions.builder()
				.song(song)
				.artist(song.getArtist())
				.emotionType(emotionType)
				.build());
			songRepository.incrementEmotionCount(song.getId(), artistId, emotionType.getName());
		}
	}
}
