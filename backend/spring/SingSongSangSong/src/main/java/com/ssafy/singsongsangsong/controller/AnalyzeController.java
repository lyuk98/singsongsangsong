package com.ssafy.singsongsangsong.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.singsongsangsong.constants.FileType;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.dto.UploadMainPageDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.exception.song.AlreadyCompletedException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.security.ArtistPrincipal;
import com.ssafy.singsongsangsong.service.AnalyzeService;
import com.ssafy.singsongsangsong.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

	private final AnalyzeService analyzeService;
	private final FileService fileService;
	private final ArtistRepository artistRepository;

	@PatchMapping("/song/{id}")
	// @CrossOrigin(origins = corsConfig.getAllowedOrigins())
	public void completeAnalyze(Long id) throws AlreadyCompletedException {
		analyzeService.completeAnalyze(id);
	}

	@GetMapping("/")
	public UploadMainPageDto getUploadMainPage(
		@AuthenticationPrincipal ArtistPrincipal user) {
		return analyzeService.getUploadStatus(user.getId());
	}

	@PostMapping("/{songId}")
	public void requestAnalyze(@AuthenticationPrincipal ArtistPrincipal user,
		@PathVariable Long songId) {
		analyzeService.requestAnalyze(user.getId(), songId);
	}

	@PostMapping("/upload")
	public void uploadMusic(@AuthenticationPrincipal ArtistPrincipal user,
		@RequestBody MultipartFile fileData) throws IOException {
		// check if MEDIA_TYPE is valid
		fileService.saveFile(user.getId(), FileType.AUDIO, fileData);
	}

	@PutMapping("/publish/{songId}")
	public void publishSong(@AuthenticationPrincipal ArtistPrincipal user, @PathVariable Long songId) {
		analyzeService.publishSong(songId);
	}

	@GetMapping("/{songId}")
	public SimpleSongDto getSongsAnalistics(@AuthenticationPrincipal ArtistPrincipal user, @PathVariable Long songId) {
		return analyzeService.getSongAnalistics(songId);
	}
}
