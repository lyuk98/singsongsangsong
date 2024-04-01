package com.ssafy.singsongsangsong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Play {
	
	private long songId;
	private String genre;
	private String atmosphere;
	private String age;
	private String sex;

}
