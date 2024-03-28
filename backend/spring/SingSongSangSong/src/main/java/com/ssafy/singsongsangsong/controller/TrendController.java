package com.ssafy.singsongsangsong.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.EmotionSongsDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.dto.TrendSongDto;
import com.ssafy.singsongsangsong.service.TrendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trend")
public class TrendController {
	
	private final TrendService trendService;

	@GetMapping("/weekly")
	public List<TrendSongDto> getWeeklyChart() {
		return trendService.getWeeklyChart().getRank();
	}
	
	@GetMapping("/genre")
	public List<TrendSongDto> getGenreSongLank(@RequestParam String genre) {
		return trendService.getGenreSongLank(genre).getRank();
	}
	
	@GetMapping("/atmos")
	public List<TrendSongDto> getAtmosphereSongLank(@RequestParam String atmosphere) {
		return trendService.getAtmosphereSongLank(atmosphere).getRank();
	}
	
	@GetMapping("/world")
	public List<TrendSongDto> getWorldChart() {
		return trendService.getWorldChart().getRank();
	}
	
	@GetMapping("/korean")
	public List<TrendSongDto> getKoreanChart() {
		return trendService.getKoreanChart().getRank();
	}
	
	@GetMapping("/emotion")
	public EmotionSongsDto getEmotionSongs() {
		return trendService.getEmotionSongs();
	}
	
	@GetMapping("/bpm")
	public TrendChartDto getBpmLank(@RequestParam int bpm) {
		return trendService.getBpmLank(bpm);
	}

}
