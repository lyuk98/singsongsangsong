package com.ssafy.singsongsangsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.EmotionSongsDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.repository.mongo.trend.TrendRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {
	
	private final TrendRepository trendRepository;
	
	@Override
	public TrendChartDto getWeeklyChart() {
		return trendRepository.getWeeklyChart();
	}

	@Override
	public TrendChartDto getGenreSongLank(String genre) {
		return trendRepository.getGenreSongLank(genre);
	}

	@Override
	public List<ArtistInfoDto> getGenreArtistLank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrendChartDto getAtmosphereSongLank(String atmosphere) {
		return trendRepository.getAtmosphereSongLank(atmosphere);
	}

	@Override
	public List<ArtistInfoDto> getAtmosphereArtistLank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrendChartDto getWorldChart() {
		return trendRepository.getWorldChart();
	}

	@Override
	public TrendChartDto getKoreanChart() {
		return trendRepository.getKoreanChart();
	}

	@Override
	public EmotionSongsDto getEmotionSongs() {
		return trendRepository.getEmotionSongs();
	}

	@Override
	public TrendChartDto getBpmLank(int bpm) {
		return trendRepository.getBpmLank(bpm);
	}

}
