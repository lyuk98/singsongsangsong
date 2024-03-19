package com.ssafy.singsongsangsong.dto;

import com.ssafy.singsongsangsong.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleSongDto {
	private Long songId;
	private String title;
	private String genre;
	private String atmosphere;
	private int playCount;
	private long artistId;
	private String artistName;
	private int duration;

	public static SimpleSongDto from(Song song) {
		return SimpleSongDto.builder()
				.songId(song.getId())
				.title(song.getTitle())
				.genre(song.getCustomGenre())
				.atmosphere(song.getAtmospheres().getFirst().getAtmosphere())
				.playCount(song.getPlayCount())
				.artistId(song.getArtist().getId())
				.artistName(song.getArtist().getNickname())
				.duration(song.getDuration())
				.build();
	}
}
