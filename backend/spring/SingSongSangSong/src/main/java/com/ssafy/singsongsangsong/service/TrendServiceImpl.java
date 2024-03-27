package com.ssafy.singsongsangsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
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
	public List<SimpleSongDto> getAtmosphereSongLank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArtistInfoDto> getAtmosphereArtistLank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SimpleSongDto> getWorldChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArtistInfoDto> getKoreanChart() {
		// TODO Auto-generated method stub
		return null;
	}

}
