package com.ssafy.singsongsangsong.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayCountDto {

	private Map<Long, Long> songCount;
	private Map<String, Long> genreCount;
	private Map<String, Long> atmosphereCount;
	
}
