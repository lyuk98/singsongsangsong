package com.ssafy.singsongsangsong.service;

import java.util.List;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;

public interface ArtistService {

	public ArtistInfoDto getArtistInfo(Long artistId);

	public List<SimpleSongDto> getPublishedSong(Long artistId);

}
