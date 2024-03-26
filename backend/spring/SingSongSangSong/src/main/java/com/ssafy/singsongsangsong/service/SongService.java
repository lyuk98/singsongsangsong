package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;

public interface SongService {

	void updateEmotionType(Long artistId, Long songId, EmotionsConstants emotionType) throws NoSuchFieldException;
}
