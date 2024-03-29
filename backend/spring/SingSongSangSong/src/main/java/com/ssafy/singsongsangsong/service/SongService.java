package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.dto.CommentsResponseDto;
import com.ssafy.singsongsangsong.dto.SongListByThemeResponseDto;

public interface SongService {

	void updateEmotionType(Long artistId, Long songId, EmotionsConstants emotionType) throws NoSuchFieldException;

	void postComment(Long artistId, Long songId, String content);

	CommentsResponseDto getComments(Long songId);

	SongListByThemeResponseDto getSongListByTheme(String themeName, int size);
}
