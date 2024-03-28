package com.ssafy.singsongsangsong.dto;

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
public class EmotionSongsDto {
	
	private TrendSongDto moved;
	private TrendSongDto like;
	private TrendSongDto excited;
	private TrendSongDto energized;
	private TrendSongDto funny;
	private TrendSongDto sad;

}
