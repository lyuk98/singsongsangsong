package com.ssafy.singsongsangsong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.dto.UploadMainPageDto;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.NotYetAnalyzedException;
import com.ssafy.singsongsangsong.exception.song.AlreadyCompletedException;
import com.ssafy.singsongsangsong.exception.song.NotFoundSongException;
import com.ssafy.singsongsangsong.repository.maria.atmosphere.AtmosphereRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyzeServiceImpl implements AnalyzeService {

	private final SongRepository songRepository;
	private final AtmosphereRepository atmosphereRepository;

	@Override
	@Transactional
	public void completeAnalyze(Long songId) throws AlreadyCompletedException {
		Song song = songRepository.findById(songId).orElseThrow(() -> new NotFoundSongException("해당 곡을 찾을 수 없습니다."));

		if (song.isAnalyzed()) {
			throw new AlreadyCompletedException("이미 분석이 완료된 곡입니다.");
		}
		song.setAnalyzed(true);
	}

	@Override
	public UploadMainPageDto getUploadStatus(Long artistId) {
		List<Song> songList = songRepository.getPublishedSongsByArtistId(artistId);
		List<UploadMainPageDto.UploadProcess> uploadProcesses = new ArrayList<>();
		songList.stream().forEach(song -> uploadProcesses.add(UploadMainPageDto.UploadProcess.from(song)));
		return UploadMainPageDto.builder().uploadProcesses(uploadProcesses).build();
	}

	@Transactional
	@Override
	public void publishSong(Long songId) throws NotYetAnalyzedException {
		Song song = songRepository.findById(songId).orElseThrow(() -> new NotFoundSongException("해당 곡을 찾을 수 없습니다."));
		if (!song.isAnalyzed()) {
			throw new NotYetAnalyzedException();
		}
		song.setPublished(true);
	}

	@Override
	public SimpleSongDto getSongAnalistics(Long songId) {
		Song song = songRepository.findById(songId).orElseThrow(() -> new NotFoundSongException("해당 곡을 찾을 수 없습니다."));
		SimpleSongDto dto = SimpleSongDto.from(song);
		dto.setAtmosphere(atmosphereRepository.getAtmosphereBySongId(songId).getFirst().getAtmosphere());
		return dto;
	}
}
