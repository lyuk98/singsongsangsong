package com.ssafy.singsongsangsong.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.singsongsangsong.constants.FileType;

public interface FileService {

	public String saveFile(Long artistId, FileType fileType, MultipartFile fileData) throws IOException;

	// img, audio
	// img => get ---
	// audio => get -- ?
	public Resource getFile(Long artistId, FileType fileType, String fileName) throws IOException;

}
