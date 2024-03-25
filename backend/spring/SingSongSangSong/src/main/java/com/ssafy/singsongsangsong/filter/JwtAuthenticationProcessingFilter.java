package com.ssafy.singsongsangsong.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.service.jwt.JwtService;
import com.ssafy.singsongsangsong.util.PasswordUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
	private static final String NO_CHECK_URL = "/login";

	private final JwtService jwtService;
	private final ArtistRepository artistRepository;

	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		if(request.getRequestURI().equals(NO_CHECK_URL)) {
			filterChain.doFilter(request,response);
			return;
		}
		checkAccessTokenAndAuthentication(request,response,filterChain);
	}

	public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	throws ServletException, IOException{
		log.info("checkAccessTokenAndAuthentication() 호출");
		jwtService.extractAccessToken(request)
			.filter(jwtService::isTokenValid)
			.ifPresent(accessToken -> jwtService.extractEmail(accessToken)
				.ifPresent(email -> artistRepository.findByUsername(email)
					.ifPresent(this::saveAuthentication)));

		filterChain.doFilter(request, response);
	}

	public void saveAuthentication(Artist myUser) {
		String password = myUser.getPassword();
		if(password == null) {
			password = PasswordUtil.generateRandomPassword();
		}
		UserDetails userDetails = User.builder()
			.username(myUser.getUsername())
			.password(password)
			.roles(myUser.getRole().name())
			.build();

		Authentication authentication =
			new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,
				authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
