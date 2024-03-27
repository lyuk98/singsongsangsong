package com.ssafy.singsongsangsong.repository.mongo.trend;

import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.dto.TrendSongDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TrendRepositoryImpl implements TrendRepository {
	
	private final MongoTemplate mongoTemplate;

	@Override
	public TrendChartDto getWeeklyChart() {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is("all")), TrendChartDto.class);
	}

	@Override
	public TrendChartDto getGenreSongLank(String genre) {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is(genre)), TrendChartDto.class);
	}

	@Override
	public TrendChartDto getAtmosphereSongLank(String atmosphere) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, TrendSongDto> getEmotionSongLank() {
		// TODO Auto-generated method stub
		return null;
	}

}
