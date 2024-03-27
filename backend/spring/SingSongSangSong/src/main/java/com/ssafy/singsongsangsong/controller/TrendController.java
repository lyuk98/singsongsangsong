package com.ssafy.singsongsangsong.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.TrendSongDto;
import com.ssafy.singsongsangsong.service.TrendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trend")
public class TrendController {
	
	private final TrendService trendService;

	@GetMapping
	public List<TrendSongDto> getWeeklyChart() {
		return trendService.getWeeklyChart().getRank();
	}
	
	@GetMapping
	public List<TrendSongDto> getGenreSongLank(String genre) {
		return trendService.getGenreSongLank(genre).getRank();
	}

}
