package com.ssafy.singsongsangsong.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trend")
public class AgeSexChartDto {
	
	private String age;
	private String sex;
	private List<Long> genreChart;
	private List<Long> atmosphereChart;
	private List<Long> songChart;
	private List<Long> artistChart;

}
