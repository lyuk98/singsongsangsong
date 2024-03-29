package com.ssafy.singsongsangsong.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.constants.EmotionsConstants;
import com.ssafy.singsongsangsong.dto.AnalyzeGenreAndAtmosphereResponse;
import com.ssafy.singsongsangsong.dto.CommentsResponseDto;
import com.ssafy.singsongsangsong.dto.PostCommentsDto;
import com.ssafy.singsongsangsong.dto.SongInfoResponse;
import com.ssafy.singsongsangsong.dto.SongListByThemeResponseDto;
import com.ssafy.singsongsangsong.security.ArtistAuthenticationToken;
import com.ssafy.singsongsangsong.security.ArtistPrincipal;
import com.ssafy.singsongsangsong.service.SongService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

	private final SongService songService;

	@PutMapping("/{songId}/{emotionType}")
	@PreAuthorize("hasRole('USER')")
	public void updateEmotion(@AuthenticationPrincipal ArtistPrincipal user, @PathVariable Long songId,
		@PathVariable EmotionsConstants emotionType) throws
		NoSuchFieldException {
		// 기존 사용자가 해당 노래에 대해서 emotion을 남긴 경우, 해당 노래의 emotion을 삭제하고 count down 시킨 다음,
		// 새로운 감정을 추가한다. count up++
		songService.updateEmotionType(user.getId(), songId, emotionType);
	}

	@PostMapping("/comments")
	@PreAuthorize("hasRole('USER')")
	public void postComment(@AuthenticationPrincipal ArtistAuthenticationToken user,
		PostCommentsDto dto) {
		// 댓글을 남긴다.
		songService.postComment(user.getId(), dto.getSongId(), dto.getContent());
	}

	@GetMapping("/comments/{songId}")
	public CommentsResponseDto getComments(@PathVariable Long songId) {
		return songService.getComments(songId);
	}

	/**
	 * 특정 테마에 해당하는 곡 리스트 반환
	 */
	@GetMapping("/theme/{themeName}")
	public SongListByThemeResponseDto getSongListByTheme(@PathVariable String themeName,
		@RequestParam(defaultValue = "10") int size) {
		return songService.getSongListByTheme(themeName, size);
	}

	@GetMapping("/{songId}")
	public SongInfoResponse getSong(@PathVariable Long songId) {
		return songService.getSong(songId);
	}

	// 해당 노래에 해당하는 분위기와 장르의 correlation을 가져옵니다.
	@GetMapping("/analyze/{songId}")
	public AnalyzeGenreAndAtmosphereResponse getAnalyzeGenreAndAtmosphere(@PathVariable Long songId,
		@RequestParam("defaultValue = 5") int size) {
		return songService.getAnalyzeGenreAndAtmosphere(songId, size);
	}

}
