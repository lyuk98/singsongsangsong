package com.ssafy.singsongsangsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
	private final ArtistRepository artistRepository;

	@Override
	public ArtistInfoDto getArtistInfo(Long artistId) {
		Artist artist = artistRepository.findById(artistId)
			.orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 존재하지 않습니다."));
		return ArtistInfoDto.from(artist);
	}

	@Override
	public List<SimpleSongDto> getPublishedSong(Long artistId) {
		return artistRepository.getPublishedSongsByArtistId(artistId)
			.stream()
			.map(SimpleSongDto::from)
			.toList();
	}

}
