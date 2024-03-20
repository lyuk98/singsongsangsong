package com.ssafy.singsongsangsong.service;

import java.util.List;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.exception.artist.AlreadyFollowException;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;

public interface ArtistService {

	public ArtistInfoDto getArtistInfo(Long artistId);

	public List<SimpleSongDto> getPublishedSong(Long artistId);

	public void toggleFollowArtist(String username, Long id) throws ArtistNotFoundException, AlreadyFollowException;

	public void expressFeeling(String username, Long songId, String feeling);
}
