package com.ssafy.singsongsangsong.service;

import java.time.LocalDate;

import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

public interface TrendService {
	
	TrendChartDto getAllChart(LocalDate date);
	SongArtistDto getGenreChart(LocalDate date, String genre);
	SongArtistDto getAtmosphereChart(LocalDate date, String atmosphere);
	BpmChartDto getBpmChart(LocalDate date, String bpm);

}
