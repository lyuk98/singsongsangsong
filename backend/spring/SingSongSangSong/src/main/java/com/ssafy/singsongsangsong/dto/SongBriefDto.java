package com.ssafy.singsongsangsong.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ssafy.singsongsangsong.entity.Atmosphere;
import com.ssafy.singsongsangsong.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SongBriefDto {
	private Long songId;
	private String title;
	private Long artistId;
	private String artistName;
	private String imageLocation;
	private String genre;
	private List<String> atmosphere;
	private int playCount;
	private int duration;

	public static SongBriefDto from(Song song) {
		SongBriefDto dto = new SongBriefDto();
		BeanUtils.copyProperties(song, dto);

		dto.setSongId(song.getId());
		dto.setTitle(song.getTitle());
		dto.setArtistId(song.getArtist().getId());
		dto.setArtistName(song.getArtist().getNickname());
		// dto.setImageLocation(song.getAlbumImage().getImageLocation());
		dto.setGenre(song.getCustomGenre());
		dto.setPlayCount(song.getPlayCount());
		dto.setDuration(song.getDuration());

		return dto;
	}
}
