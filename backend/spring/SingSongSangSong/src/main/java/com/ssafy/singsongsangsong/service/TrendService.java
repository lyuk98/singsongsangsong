package com.ssafy.singsongsangsong.service;

import java.util.List;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.EmotionSongsDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

public interface TrendService {
	
	public TrendChartDto getWeeklyChart();
	public TrendChartDto getGenreSongLank(String genre);
	public List<ArtistInfoDto> getGenreArtistLank();
	public TrendChartDto getAtmosphereSongLank(String atmosphere);
	public List<ArtistInfoDto> getAtmosphereArtistLank();
	public TrendChartDto getWorldChart();
	public TrendChartDto getKoreanChart();
	public EmotionSongsDto getEmotionSongs();
	public TrendChartDto getBpmLank(int bpm);

}
