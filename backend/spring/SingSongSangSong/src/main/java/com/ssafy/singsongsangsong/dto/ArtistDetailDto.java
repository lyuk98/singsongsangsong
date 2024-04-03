package com.ssafy.singsongsangsong.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDetailDto {
	private ArtistInfoDto artistInfoDto;
	private int countPublishedSong;

	public static ArtistDetailDto from(ArtistInfoDto artistInfoDto, int countPublishedSong) {
		ArtistDetailDto artistDetailDto = new ArtistDetailDto();
		artistDetailDto.setArtistInfoDto(artistInfoDto);
		artistDetailDto.setCountPublishedSong(countPublishedSong);
		return artistDetailDto;
	}
}
