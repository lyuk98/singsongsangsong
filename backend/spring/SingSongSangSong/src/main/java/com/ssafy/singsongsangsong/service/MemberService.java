package com.ssafy.singsongsangsong.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.ssafy.singsongsangsong.dto.GuestJoinRequestDto;

public interface MemberService {
	void join(String username, GuestJoinRequestDto dto);
	// void logout();
}
