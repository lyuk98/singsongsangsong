package com.ssafy.singsongsangsong.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

// todo: 아래 클래스는 OAuth2 구현이 끝나고, 인증 객체를 받아서 사용자 정보를 저장하는 클래스로 변경해야 합니다.
public class ArtistAuthenticationToken extends AbstractAuthenticationToken {
	private Long id;

	public Long getId() {
		return id;
	}

	public ArtistAuthenticationToken(
		Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
}
