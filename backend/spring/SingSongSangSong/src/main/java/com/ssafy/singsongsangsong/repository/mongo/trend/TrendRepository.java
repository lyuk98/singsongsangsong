package com.ssafy.singsongsangsong.repository.mongo.trend;

import com.ssafy.singsongsangsong.dto.EmotionSongsDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

public interface TrendRepository {
	
	TrendChartDto getWeeklyChart();
	TrendChartDto getGenreSongLank(String genre);
	TrendChartDto getAtmosphereSongLank(String atmosphere);
	TrendChartDto getWorldChart();
	TrendChartDto getKoreanChart();
	EmotionSongsDto getEmotionSongs();
	TrendChartDto getBpmLank(int bpm);
	
}
