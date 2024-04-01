package com.ssafy.singsongsangsong.dto;

import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreCountDto {

	private TreeMap<String, Long> genreCount;
	
	public GenreCountDto() {
		this.genreCount = new TreeMap<>();
		
		this.genreCount.put("ballad", 0L);
		this.genreCount.put("rock", 0L);
		this.genreCount.put("jazz", 0L);
	}
	
}
