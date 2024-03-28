package com.ssafy.singsongsangsong.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

// todo: 아래 클래스는 OAuth2 구현이 끝나고, 인증 객체를 받아서 사용자 정보를 저장하는 클래스로 변경해야 합니다.

@Getter
@Setter
public class ArtistAuthenticationToken extends AbstractAuthenticationToken {
	private Long id;

	private ArtistPrincipal artistPrincipal;

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
		return artistPrincipal;
	}
}
