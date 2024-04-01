package com.ssafy.singsongsangsong.dto;

import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtmosphereCountDto {
	
	private TreeMap<String, Long> atmosphereCount;
	
	public AtmosphereCountDto() {
		this.atmosphereCount = new TreeMap<>();
	}

}
