package com.ssafy.singsongsangsong.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.ssafy.singsongsangsong.ArtistFixture;
import com.ssafy.singsongsangsong.dto.LikedPageResponseDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Image;
import com.ssafy.singsongsangsong.entity.Likes;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;
import com.ssafy.singsongsangsong.util.Role;

@SpringBootTest
public class songPlayListServiceTest {
	@Autowired
	ArtistRepository artistRepository;
	@Autowired
	SongRepository songRepository;
	@Autowired
	SongPlayListService songPlayListService;
	@Autowired
	ArtistFixture artistFixture;
	@BeforeEach
	public void beforeEach(){
		Artist artist = artistFixture.getArtist();

		Song song = Song.builder()
			.id(1L)
			.artist(artist)
			.title("great escape")
			.build();

		artistRepository.save(artist);
		songRepository.save(song);
	}

	@Test
	void LikedSongs() {
		// given
		Artist artist = artistRepository.findByUsername("id999").orElse(null);
		Song song = songRepository.findById(1L).orElse(null);
		Likes likes = new Likes(1L, song,artist);

		LikedPageResponseDto response = songPlayListService.getLikedPagination("id999",1);
		Assertions.assertEquals(response.getLikedSongPage().get(0).getArtistName(),"validUser");
	}
}
