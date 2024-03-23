package com.ssafy.singsongsangsong.service;

import com.ssafy.singsongsangsong.dto.UploadMainPageDto;
import com.ssafy.singsongsangsong.exception.song.AlreadyCompletedException;

public interface AnalyzeService {
	public void completeAnalyze(Long songId) throws AlreadyCompletedException;

	public UploadMainPageDto getUploadStatus(Long artistId);
}
