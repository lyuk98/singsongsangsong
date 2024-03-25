package com.ssafy.singsongsangsong.controller;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.ssafy.singsongsangsong.exception.song.AlreadyCompletedException;
import com.ssafy.singsongsangsong.security.ArtistAuthenticationToken;
import com.ssafy.singsongsangsong.service.AnalyzeService;
import com.ssafy.singsongsangsong.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

	private final AnalyzeService analyzeService;
	private final FileService fileService;

	@PatchMapping("/song/{id}")
	// @CrossOrigin(origins = corsConfig.getAllowedOrigins())
	public void completeAnalyze(Long id) throws AlreadyCompletedException {
		analyzeService.completeAnalyze(id);
	}

	@GetMapping("/")
	public UploadMainPageDto getUploadMainPage(
		@AuthenticationPrincipal ArtistAuthenticationToken artistAuthenticationToken) {
		return analyzeService.getUploadStatus(artistAuthenticationToken.getId());
	}

	@PostMapping("/upload")
	public void uploadMusic(@AuthenticationPrincipal ArtistAuthenticationToken artist,
		@RequestBody MultipartFile fileData) throws IOException {
		// check if MEDIA_TYPE is valid
		fileService.saveFile(artist.getId(), FileType.AUDIO, fileData);
	}

	@GetMapping("/test")
	public ResponseEntity<Resource> getImage(@AuthenticationPrincipal ArtistAuthenticationToken artist) throws
		IOException {
		Resource body = fileService.getFile(artist.getId(), FileType.IMAGE, "profile.jpg");
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(body);
	}

	@PutMapping("/publish/{songId}")
	public void publishSong(@AuthenticationPrincipal ArtistAuthenticationToken artist, @PathVariable Long songId) {
		analyzeService.publishSong(songId);
	}

	@GetMapping("/{songId}")
	public SimpleSongDto getSongsAnalistics(@AuthenticationPrincipal ArtistAuthenticationToken
		artist, @PathVariable Long songId) {
		return analyzeService.getSongAnalistics(songId);
	}
}
