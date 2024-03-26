package com.ssafy.singsongsangsong.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;

@RestController
public class SongController {

	@PutMapping("/song/{songId}/{emotionType}")
	public void updateEmotion(@PathVariable Long songId, @PathVariable EmotionsConstants emotionType) {
		// 기존 사용자가 해당 노래에 대해서 emotion을 남긴 경우, 해당 노래의 emotion을 삭제하고 count down 시킨 다음,
		// 새로운 감정을 추가한다. count up++

		// songService.updateEmotionType(songId, emotionType);
	}
}
