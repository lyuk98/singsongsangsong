package com.ssafy.singsongsangsong.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.EmotionsDto;
import com.ssafy.singsongsangsong.dto.GuestJoinRequestDto;
import com.ssafy.singsongsangsong.dto.JoinResponseDto;
import com.ssafy.singsongsangsong.dto.FollowerCountResponse;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.service.artist.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {
	private final ArtistService artistService;

	@PostMapping("/join")
	public JoinResponseDto join(@AuthenticationPrincipal String username, GuestJoinRequestDto dto) throws IOException {
		artistService.join(username, dto);
		return new JoinResponseDto();
	}

	@GetMapping("{id}")
	public ArtistInfoDto getArtistInfo(@PathVariable Long id) {
		return artistService.getArtistInfo(id);
	}

	@GetMapping("/song/{id}")
	public List<SimpleSongDto> getPublishedSong(@PathVariable Long id) {
		return artistService.getPublishedSong(id);
	}

	// todo: 아래 메소드는 security 구현이 끝난 이 후, 처리할 예정입니다.
	// todo: refactor this. Principal 정보 수정 및 target Id는 String username으로 변경
	@PostMapping("/follow/{id}")
	public void followArtist(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
		String username = loginUser.getUsername();
		artistService.toggleFollowArtist(username, id);
	}

	// todo: 아래 메소드는 song 데이터가 추가된 후에, 테스트 코드 작성 예정입니다.
	@GetMapping("/emotions/{id}")
	public EmotionsDto getEmotions(@PathVariable Long id) {
		return artistService.getEmotions(id);
	}

	@GetMapping("/followers/{artistId}/count")
	public FollowerCountResponse getFollowerCount(@PathVariable Long artistId) {
		return artistService.getFollowerCount(artistId);
	}
}
