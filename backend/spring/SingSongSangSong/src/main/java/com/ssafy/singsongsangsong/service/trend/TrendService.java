package com.ssafy.singsongsangsong.service.trend;

import java.time.LocalDate;

import com.ssafy.singsongsangsong.dto.AgeSexChartDto;
import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDetailDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.dto.TrendSongDto;

public interface TrendService {
	
	TrendSongDto getSong(Long songId);

	TrendChartDto getAllChart(LocalDate date);
	
	AgeSexChartDto getAgeSexChart(LocalDate date, String age, String sex);

	SongArtistDetailDto getGenreChart(LocalDate date, String genre);

	SongArtistDetailDto getAtmosphereChart(LocalDate date, String atmosphere);

	BpmChartDto getBpmChart(LocalDate date, String bpm);

}
