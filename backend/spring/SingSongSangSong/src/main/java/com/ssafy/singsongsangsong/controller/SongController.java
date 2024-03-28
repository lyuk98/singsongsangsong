package com.ssafy.singsongsangsong.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.dto.CommentsResponseDto;
import com.ssafy.singsongsangsong.dto.PostCommentsDto;
import com.ssafy.singsongsangsong.security.ArtistAuthenticationToken;
import com.ssafy.singsongsangsong.service.SongService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

	private final SongService songService;

	@PutMapping("/{songId}/{emotionType}")
	public void updateEmotion(@AuthenticationPrincipal ArtistAuthenticationToken user, @PathVariable Long songId,
		@PathVariable EmotionsConstants emotionType) throws
		NoSuchFieldException {
		// 기존 사용자가 해당 노래에 대해서 emotion을 남긴 경우, 해당 노래의 emotion을 삭제하고 count down 시킨 다음,
		// 새로운 감정을 추가한다. count up++
		songService.updateEmotionType(user.getId(), songId, emotionType);
	}

	@PostMapping("/comments")
	public void postComment(@AuthenticationPrincipal ArtistAuthenticationToken user,
		PostCommentsDto dto) {
		// 댓글을 남긴다.
		songService.postComment(user.getId(), dto.getSongId(), dto.getContent());
	}

	@GetMapping("/comments/{songId}")
	public CommentsResponseDto getComments(@PathVariable Long songId) {
		return songService.getComments(songId);
	}

}
