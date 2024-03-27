package com.ssafy.singsongsangsong.repository.mongo.trend;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.dto.EmotionSongsDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;

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
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is(atmosphere)), TrendChartDto.class);
	}

	@Override
	public TrendChartDto getWorldChart() {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is("world")), TrendChartDto.class);
	}

	@Override
	public TrendChartDto getKoreanChart() {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is("korean")), TrendChartDto.class);
	}
	
	@Override
	public EmotionSongsDto getEmotionSongs() {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is("emotions")), EmotionSongsDto.class);
	}

	@Override
	public TrendChartDto getBpmLank(int bpm) {
		return mongoTemplate.findOne(Query.query(Criteria.where("part").is(bpm)), TrendChartDto.class);
	}

}
