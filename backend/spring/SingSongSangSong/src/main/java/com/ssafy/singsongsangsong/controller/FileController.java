package com.ssafy.singsongsangsong.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.singsongsangsong.constants.FileType;
import com.ssafy.singsongsangsong.dto.UploadFileDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.exception.song.NotFoundSongException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.security.ArtistAuthenticationToken;
import com.ssafy.singsongsangsong.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;
	private final ArtistRepository artistRepository;
	@PostMapping("/upload/{fileType}")
	public UploadFileDto uploadFile(@AuthenticationPrincipal String username,
		@PathVariable String fileType, MultipartFile fileData) throws IOException {
		Artist artist = artistRepository.findByUsername(username).orElseThrow(() -> new ArtistNotFoundException("유효하지 않은 유저입니다."));
		FileType type = FileType.valueOf(fileType);

		return new UploadFileDto(fileService.saveFile(artist.getId(), type, fileData), fileData.getOriginalFilename());
	}

	@GetMapping("/download/{fileType}/{fileName}")
	public void downloadFile(@AuthenticationPrincipal String username,
		@PathVariable String fileType, @PathVariable String fileName) throws IOException {
		Artist artist = artistRepository.findByUsername(username).orElseThrow(() -> new ArtistNotFoundException("유효하지 않은 유저입니다."));
		FileType type = FileType.valueOf(fileType);
		fileService.getFile(artist.getId(), type, fileName);
	}

}
