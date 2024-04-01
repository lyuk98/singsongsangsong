package com.ssafy.singsongsangsong.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.singsongsangsong.constants.FileType;
import com.ssafy.singsongsangsong.entity.File;
import com.ssafy.singsongsangsong.exception.file.NotFoundFileException;
import com.ssafy.singsongsangsong.repository.maria.file.FileRepository;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class MinioFileService implements FileService {

	private final MinioClient minioClient;
	private final FileRepository fileRepository;

	@Override
	@Transactional
	public String saveFile(Long artistId, FileType fileType, MultipartFile fileData) throws IOException {
		String savedFileName = uploadToMinIo(fileType.getName(), fileData);
		log.info("original: {} => saved: {}", fileData.getOriginalFilename(), savedFileName);
		fileRepository.save(File.of(savedFileName, fileData.getOriginalFilename(), artistId));
		return savedFileName;
	}

	@Override
	public Resource getFile(Long artistId, FileType fileType, String originalFileName) throws IOException {
		File file = fileRepository.findByOriginalFileName(originalFileName).orElseThrow(NotFoundFileException::new);
		log.info("getFile => originalFileName: {}", originalFileName);
		try (InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
			.bucket(fileType.getName())
			.object(file.getSavedFileName())
			.build())) {
			return new ByteArrayResource(inputStream.readAllBytes());
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}

	private String uploadToMinIo(String bucket, MultipartFile file) {
		String savedFileName = UUID.randomUUID().toString();

		try {
			minioClient.putObject(PutObjectArgs.builder()
				.bucket(bucket)
				.object(savedFileName)
				.stream(file.getInputStream(), file.getSize(), -1)
				.contentType(file.getContentType())
				.build());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return savedFileName;
	}

}
