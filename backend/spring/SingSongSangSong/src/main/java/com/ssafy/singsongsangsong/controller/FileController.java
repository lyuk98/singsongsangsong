package com.ssafy.singsongsangsong.controller;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ssafy.singsongsangsong.constants.FileType;
import com.ssafy.singsongsangsong.dto.UploadFileDto;
import com.ssafy.singsongsangsong.security.ArtistPrincipal;
import com.ssafy.singsongsangsong.service.file.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

	private final FileService fileService;

	@PostMapping("/upload/{fileType}")
	public UploadFileDto uploadFile(@AuthenticationPrincipal ArtistPrincipal user,
		@PathVariable("fileType") FileType fileType, @RequestBody MultipartFile fileData) throws IOException {

		if (user == null) {
			throw new JWTVerificationException("로그인이 필요합니다");
		}
		return new UploadFileDto(fileService.saveFile(user.getId(), fileType, fileData),
			fileData.getOriginalFilename());
	}

	@GetMapping("/download/{fileType}/{originalFileName}")
	public ResponseEntity<Resource> downloadFile(@AuthenticationPrincipal ArtistPrincipal user,
		@PathVariable("fileType") FileType fileType, @PathVariable("originalFileName") String originalFileName) throws
		IOException {

		if (user == null) {
			throw new JWTVerificationException("로그인이 필요합니다");
		}

		log.info("fileType: {}", fileType);
		log.info("originalFileName: {}", originalFileName);

		Resource file = fileService.getFile(user.getId(), fileType, originalFileName);
		return ResponseEntity.ok()
			.contentType(MediaType.IMAGE_JPEG)
			.body(file);
	}

}
