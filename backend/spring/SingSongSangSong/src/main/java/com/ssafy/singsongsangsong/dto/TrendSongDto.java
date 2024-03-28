package com.ssafy.singsongsangsong.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrendSongDto {
	
	private long songId;
	private String title;
	private long artistId;
	private String artistName;
	private String lyrics;
	private long play;
	private long download;
	private long like;
	private Map<String, Integer> emotions;
	private Map<String, Double> atmospheres;

}
