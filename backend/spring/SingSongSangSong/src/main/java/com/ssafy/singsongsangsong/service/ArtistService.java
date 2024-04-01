package com.ssafy.singsongsangsong.service;

import java.util.List;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.EmotionsDto;
import com.ssafy.singsongsangsong.dto.FollowerCountResponse;
import com.ssafy.singsongsangsong.dto.MyProfileResponse;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.exception.artist.AlreadyFollowedException;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;

public interface ArtistService {

	public ArtistInfoDto getArtistInfo(Long artistId);

	public List<SimpleSongDto> getPublishedSong(Long artistId);

	public void toggleFollowArtist(String username, Long id) throws ArtistNotFoundException, AlreadyFollowedException;

	public EmotionsDto getEmotions(Long artistId);

	public FollowerCountResponse getFollowerCount(Long artistId);

	public MyProfileResponse getMyProfile(Long id);
}
