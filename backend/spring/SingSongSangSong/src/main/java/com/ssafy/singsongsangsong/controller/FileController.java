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
import com.ssafy.singsongsangsong.security.ArtistAuthenticationToken;
import com.ssafy.singsongsangsong.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;

	@PostMapping("/upload/{fileType}")
	public UploadFileDto uploadFile(@AuthenticationPrincipal ArtistAuthenticationToken artist,
		@PathVariable String fileType, MultipartFile fileData) throws IOException {

		FileType type = FileType.valueOf(fileType);

		return new UploadFileDto(fileService.saveFile(artist.getId(), type, fileData), fileData.getOriginalFilename());
	}

	@GetMapping("/download/{fileType}/{fileName}")
	public void downloadFile(@AuthenticationPrincipal ArtistAuthenticationToken artist,
		@PathVariable String fileType, @PathVariable String fileName) throws IOException {
		FileType type = FileType.valueOf(fileType);
		fileService.getFile(artist.getId(), type, fileName);
	}

}
