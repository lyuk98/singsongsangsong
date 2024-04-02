package com.ssafy.singsongsangsong.service.file;

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
import com.ssafy.singsongsangsong.dto.UploadSongDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.File;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.exception.file.NotFoundFileException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.repository.maria.file.FileRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;

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
	private final SongRepository songRepository;
	private final ArtistRepository artistRepository;

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

	@Override
	@Transactional
	public UploadSongDto uploadSong(Long artistId, FileType fileType, MultipartFile fileData) throws IOException {
		String savedFileName = saveFile(artistId, fileType, fileData);
		Artist artist = artistRepository.findById(artistId).orElseThrow(ArtistNotFoundException::new);
		Song song = songRepository.save(
			Song.builder().artist(artist).musicFileName(fileData.getOriginalFilename()).build());
		return new UploadSongDto(song.getId(), fileData.getOriginalFilename(), savedFileName);
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
