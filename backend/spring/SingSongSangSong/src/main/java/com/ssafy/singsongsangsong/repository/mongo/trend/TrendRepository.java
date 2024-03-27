package com.ssafy.singsongsangsong.repository.mongo.trend;

import java.util.Map;

import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.dto.TrendSongDto;

public interface TrendRepository {
	
	TrendChartDto getWeeklyChart();
	TrendChartDto getGenreSongLank(String genre);
	TrendChartDto getAtmosphereSongLank(String atmosphere);
	Map<String, TrendSongDto> getEmotionSongLank();
	
}
