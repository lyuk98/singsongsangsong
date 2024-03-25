package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.dto.GuestJoinRequestDto;

public interface JoinService {
	void join(String username, GuestJoinRequestDto guestJoinRequestDto);
}
