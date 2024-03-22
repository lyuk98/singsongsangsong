package com.ssafy.singsongsangsong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	private JoinService joinService;
	private ArtistRepository artistRepository;
	@GetMapping("/")
	public SimpleSongDto main() {
		return SimpleSongDto.builder().genre("test").build();
	}
}
