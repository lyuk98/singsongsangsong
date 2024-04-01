package com.ssafy.singsongsangsong.dto;

import java.util.List;
import java.util.Map;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishedResponseDto {
	private List<SimpleSongDto> list;
	private Map<String,Integer> genreCount;
	private Map<String,Integer> atmosphereCount;
}
