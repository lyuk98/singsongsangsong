package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.dto.UploadMainPageDto;
import com.ssafy.singsongsangsong.exception.song.AlreadyCompletedException;

public interface AnalyzeService {
	public void completeAnalyze(Long songId) throws AlreadyCompletedException;

	public UploadMainPageDto getUploadStatus(Long artistId);

	public void publishSong(Long songId);

	public SimpleSongDto getSongAnalistics(Long songId);
}
