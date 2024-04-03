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
	private List<Integer> genreChart;
	private List<Integer> atmosphereChart;
	private List<Integer> songChart;
	private List<Integer> artistChart;

}
