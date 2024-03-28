package com.ssafy.singsongsangsong.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.PostCommentsDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.service.JoinService;
import com.ssafy.singsongsangsong.util.Role;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	private JoinService joinService;
	private ArtistRepository artistRepository;
	@GetMapping("/")
	public PostCommentsDto main() {
		return new PostCommentsDto(1L,"asdf");
	}
}
