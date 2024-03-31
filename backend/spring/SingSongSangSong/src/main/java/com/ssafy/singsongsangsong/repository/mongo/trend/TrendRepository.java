package com.ssafy.singsongsangsong.repository.mongo.trend;

import java.time.LocalDate;

import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

public interface TrendRepository {
	
	TrendChartDto getAllChart(LocalDate date);
	SongArtistDto getGenreChart(LocalDate date, String genre);
	SongArtistDto getAtmosphereChart(LocalDate date, String atmosphere);
	BpmChartDto getBpmChart(LocalDate date, String bpm);
	
}
