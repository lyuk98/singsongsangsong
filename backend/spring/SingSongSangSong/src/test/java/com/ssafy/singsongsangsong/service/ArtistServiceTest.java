package com.ssafy.singsongsangsong.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Image;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;

@SpringBootTest()
@ActiveProfiles("test")
public class ArtistServiceTest {

	@Autowired
	ArtistService artistService;

	@Autowired
	ArtistRepository artistRepository;

	static Artist validArtist;
	static Artist invalidArtist;


	@BeforeAll
	public static void init() {
		validArtist = Artist.builder()
			.id(1L)
			.age(20)
			.sex('M')
			.nickname("nickname")
			.username("username")
			.password("1234")
			.introduction("introduction")
			.profileImage(null)
			.build();
	}

	@Test
	public void getArtistInfoTest() {
		Artist saved = artistRepository.save(validArtist);

		Image profileImage = Optional.ofNullable(saved.getProfileImage()).orElseGet(() -> null);
		String profileImageUrl = Optional.ofNullable(profileImage).map(Image::getImageLocation).orElseGet(() -> null);

		ArtistInfoDto artistInfo = artistService.getArtistInfo(saved.getId());
		assertThat(artistInfo.getArtistId()).isEqualTo(saved.getId());
		assertThat(artistInfo.getIntroduction()).isEqualTo(saved.getIntroduction());
		assertThat(artistInfo.getNickname()).isEqualTo(saved.getNickname());
		assertThat(artistInfo.getProfileImageUrl()).isEqualTo(profileImageUrl);
		assertThat(artistInfo.getUsername()).isEqualTo(saved.getUsername());
	}

	@Test
	public void getPublishedSongTest() {
		// TODO: add test for getPublishedSong
	}

}
