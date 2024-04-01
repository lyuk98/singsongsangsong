package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.dto.AnalyzeGenreAndAtmosphereResponse;
import com.ssafy.singsongsangsong.dto.CommentsResponseDto;
import com.ssafy.singsongsangsong.dto.SongInfoResponse;
import com.ssafy.singsongsangsong.dto.SongListByThemeResponseDto;
import com.ssafy.singsongsangsong.dto.SongSimilarityByRanksResponse;

public interface SongService {

	void updateEmotionType(Long artistId, Long songId, EmotionsConstants emotionType) throws NoSuchFieldException;

	void postComment(Long artistId, Long songId, String content);

	CommentsResponseDto getComments(Long songId);

	SongListByThemeResponseDto getSongListByTheme(String themeName, int size);

	SongInfoResponse getSong(Long songId);

	AnalyzeGenreAndAtmosphereResponse getAnalyzeGenreAndAtmosphere(Long songId, int size);

	void playSong(Long artistId, Long songId);

	// TODO: 파일 형식으로 클라이언트가 다운로드 받을 수 있게끔 구현 수정
	void downloadSong(Long artistId, Long songId);

	SongSimilarityByRanksResponse getSongsSimilarityByRanks(Long songId, int size);
}
