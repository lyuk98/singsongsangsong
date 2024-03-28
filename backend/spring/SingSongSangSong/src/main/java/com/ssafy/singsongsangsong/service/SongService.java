package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.dto.CommentsResponseDto;

public interface SongService {

	void updateEmotionType(Long artistId, Long songId, EmotionsConstants emotionType) throws NoSuchFieldException;

	void postComment(Long artistId, Long songId, String content);

	CommentsResponseDto getComments(Long songId);
}
