package com.ssafy.singsongsangsong.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.service.trend.TrendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trend")
public class TrendController {

	private final TrendService trendService;

	@GetMapping("/all")
	public TrendChartDto getAllChart(@RequestParam("date") String date) {
		return trendService.getAllChart(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
	}

	@GetMapping("/genre")
	public SongArtistDto getGenreChart(@RequestParam("date") String date, @RequestParam("genre") String genre) {
		return trendService.getGenreChart(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), genre);
	}

	@GetMapping("/atmosphere")
	public SongArtistDto getAtmosphereChart(@RequestParam("date") String date,
		@RequestParam("atmosphere") String atmosphere) {
		return trendService.getAtmosphereChart(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), atmosphere);
	}

	@GetMapping("/bpm")
	public BpmChartDto getBpmChart(@RequestParam("date") String date, @RequestParam("bpm") String bpm) {
		return trendService.getBpmChart(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), bpm);
	}

}
