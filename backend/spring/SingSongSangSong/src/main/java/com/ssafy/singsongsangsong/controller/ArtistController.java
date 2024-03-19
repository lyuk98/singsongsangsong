package com.ssafy.singsongsangsong.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.service.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {
	private final ArtistService artistService;

	@GetMapping("{id}")
	public ArtistInfoDto getArtistInfo(@PathVariable Long id) {
		return artistService.getArtistInfo(id);
	}

	@GetMapping("/song/{id}")
	public List<SimpleSongDto> getPublishedSong(@PathVariable Long id) {
		return artistService.getPublishedSong(id);
	}

	// todo: 아래 메소드는 security 구현이 끝난 이 후, 처리할 예정입니다.
	@PostMapping("/follow/{id}")
	public void followArtist(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
		String username = loginUser.getUsername();
		artistService.toggleFollowArtist(username, id);
	}
}
