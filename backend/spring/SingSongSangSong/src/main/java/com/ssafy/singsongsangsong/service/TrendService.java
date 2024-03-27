package com.ssafy.singsongsangsong.service;

import java.util.List;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

public interface TrendService {
	
	public TrendChartDto getWeeklyChart();
	public TrendChartDto getGenreSongLank(String genre);
	public List<ArtistInfoDto> getGenreArtistLank();
	public List<SimpleSongDto> getAtmosphereSongLank();
	public List<ArtistInfoDto> getAtmosphereArtistLank();
	public List<SimpleSongDto> getWorldChart();
	public List<ArtistInfoDto> getKoreanChart();

}
