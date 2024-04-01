package com.ssafy.singsongsangsong.service.trend;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.repository.mongo.trend.TrendRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {

	private final TrendRepository trendRepository;

	@Override
	public TrendChartDto getAllChart(LocalDate date) {
		return trendRepository.getAllChart(date);
	}

	@Override
	public SongArtistDto getGenreChart(LocalDate date, String genre) {
		return trendRepository.getGenreChart(date, genre);
	}

	@Override
	public SongArtistDto getAtmosphereChart(LocalDate date, String atmosphere) {
		return trendRepository.getAtmosphereChart(date, atmosphere);
	}

	@Override
	public BpmChartDto getBpmChart(LocalDate date, String bpm) {
		return trendRepository.getBpmChart(date, bpm);
	}

}
