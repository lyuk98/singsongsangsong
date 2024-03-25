package com.ssafy.singsongsangsong.service;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.GuestJoinRequestDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.util.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService{
	private final ArtistRepository artistRepository;
	@Override
	public void join(String username,GuestJoinRequestDto guestJoinRequestDto) {
		Artist artist = artistRepository.findByUsername(username).orElseThrow();
		artist.setAge(guestJoinRequestDto.getAge());
		artist.setIntroduction(guestJoinRequestDto.getIntroduction());
		artist.setNickname(guestJoinRequestDto.getNickname());
		artist.setRole(Role.USER);
		artist.setSex(guestJoinRequestDto.getSex());
		artistRepository.save(artist);
	}
}
