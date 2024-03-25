package com.ssafy.singsongsangsong.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.LikedPageResponseDto;
import com.ssafy.singsongsangsong.dto.SearchResponseDto;
import com.ssafy.singsongsangsong.dto.SongBriefDto;
import com.ssafy.singsongsangsong.service.SongPlayListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/music-playlist")
@RequiredArgsConstructor
public class SongPlayListController {
	private final SongPlayListService songPlayListService;
	@GetMapping("/liked-song/{pageNo}")
	public LikedPageResponseDto getLikedPagination(@AuthenticationPrincipal String username, @PathVariable int pageNo) {
		return songPlayListService.getLikedPagination(username,pageNo);
	}

	@GetMapping("/weekly-hitsong")
	public List<SongBriefDto> getWeeklyHitSongList() {
		return songPlayListService.getWeeklyHitSongList();
	}

	@GetMapping("/genre-hitsong/{genre}")
	public List<SongBriefDto> getGenreHitSongList(@PathVariable String genre) { return songPlayListService.getGenreHitSongList(genre);}

	@GetMapping("/atmosphere-hitsong/{atmosphere}")
	public List<SongBriefDto> getAtmosphereHitSongList(@PathVariable String atmosphere) { return songPlayListService.getAtmosphereHitSongList(atmosphere);}

	@GetMapping("/hot-artist")
	public List<ArtistInfoDto> getHotArtist(@AuthenticationPrincipal String username) {

	}

	@GetMapping("/search")
	public SearchResponseDto SearchArtistAndSong(@RequestParam String keyword,
												@RequestParam String genre,
												@RequestParam String atmosphere,
												@RequestParam int bpm,
												@RequestParam String sort) {

	}
}
