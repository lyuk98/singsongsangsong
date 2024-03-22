package com.ssafy.singsongsangsong.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.service.jwt.JwtService;
import com.ssafy.singsongsangsong.util.CustomOAuth2User;
import com.ssafy.singsongsangsong.util.Role;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtService jwtService;
	private final ArtistRepository artistRepository;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("OAuth2 Login 성공");
		try{
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
			if(oAuth2User.getRole() == Role.GUEST) {
				String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
				// response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
				// jwtService.sendAccessTokenAndRefreshToken(response,"Bearer " +  accessToken, null);
				//
				// response.sendRedirect("/sign-up");
				response.addCookie(createCookie("accessToken", accessToken,"/",60*60*60*60));
				response.addCookie(createCookie("sendRedirect", "/sign-up","/",1));
			} else {
				loginSuccess(response, oAuth2User);
				response.sendRedirect("/");
			}
		} catch(Exception e) {
			throw e;
		}
	}

	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
		Artist user = artistRepository.findByUsername(oAuth2User.getEmail()).orElseThrow();
		Long userId = user.getId();

		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());

		response.addCookie(createCookie("accessToken", accessToken,"/",60*60*60*60));
		// jwtService.updateRefreshToken(oAuth2User.getEmail(),refreshToken);
	}

	public Cookie createCookie(String key, String value,String path,int time) {
		Cookie cookie = new Cookie(key,value);
		cookie.setMaxAge(time);
		cookie.setPath(path);
		// cookie.setHttpOnly(true);

		return cookie;
	}
}
