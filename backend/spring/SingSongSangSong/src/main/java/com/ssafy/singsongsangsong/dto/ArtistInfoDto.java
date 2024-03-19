package com.ssafy.singsongsangsong.dto;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.singsongsangsong.config.ModelMapperConfig;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ArtistInfoDto {
	private Long artistId;
	private String nickname;
	private String username;
	private String profileImageUrl;
	private String introduction;

	public static ArtistInfoDto from(Artist artist) {
		Image profileImage = Optional.ofNullable(artist.getProfileImage()).orElseGet(() -> null);
		String profileImageUrl = Optional.ofNullable(profileImage).map(Image::getImageLocation).orElseGet(() -> null);
		return ArtistInfoDto.builder()
			.artistId(artist.getId())
			.nickname(artist.getNickname())
			.username(artist.getUsername())
			.profileImageUrl(profileImageUrl)
			.introduction(artist.getIntroduction())
			.build();
	}

}
