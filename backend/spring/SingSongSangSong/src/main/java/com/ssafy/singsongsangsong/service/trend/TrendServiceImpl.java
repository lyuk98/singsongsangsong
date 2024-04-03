package com.ssafy.singsongsangsong.service.trend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.constants.DefaultFileName;
import com.ssafy.singsongsangsong.dto.AgeSexChartDto;
import com.ssafy.singsongsangsong.dto.AllChartDto;
import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.BpmChartDto;
import com.ssafy.singsongsangsong.dto.SongArtistDetailDto;
import com.ssafy.singsongsangsong.dto.SongArtistDto;
import com.ssafy.singsongsangsong.dto.TrendChartDto;
import com.ssafy.singsongsangsong.dto.TrendSongDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.song.NotFoundSongException;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;
import com.ssafy.singsongsangsong.repository.mongo.trend.TrendRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {

	private final TrendRepository trendRepository;
	private final SongRepository songRepository;
	
	@Override
	public TrendSongDto getSong(Long songId) {
		Song song = songRepository.findById(songId).orElseThrow(NotFoundSongException::new);
		Artist artist = song.getArtist();

		String musicFileName = Optional.ofNullable(song.getMusicFileName())
			.orElseGet(DefaultFileName.DEFAULT_PROFILE_PICTURE::getName);

		String originalFileName;
		if (song.getAlbumImage() != null && song.getAlbumImage().getOriginalFileName() != null) {
			originalFileName = song.getAlbumImage().getOriginalFileName();
		} else {
			originalFileName = DefaultFileName.DEFAULT_PROFILE_PICTURE.getName();
		}
		
		return TrendSongDto.builder()
				.songId(song.getId())
				.songTitle(song.getTitle())
				.artist(ArtistInfoDto.from(artist))
				.songFileName(musicFileName)
				.albumImageFileName(originalFileName)
				.likeCount(song.getLikeCount())
				.downloadCount(song.getDownloadCount())
				.playCount(song.getPlayCount())
				.bpm(song.getBpm())
				.movedEmotionCount(song.getMovedEmotionCount())
				.likeEmotionCount(song.getLikeEmotionCount())
				.energizedEmotionCount(song.getEnergizedEmotionCount())
				.excitedEmotionCount(song.getExcitedEmotionCount())
				.funnyEmotionCount(song.getFunnyEmotionCount())
				.sadEmotionCount(song.getSadEmotionCount())
				.build();
	}

	@Override
	public TrendChartDto getAllChart(LocalDate date) {
		AllChartDto dto = trendRepository.getAllChart(date);
		TrendChartDto trendChartDto = new TrendChartDto();
		
		List<TrendSongDto> emotions = new ArrayList<>();
		
		for (long songId : dto.getEmotions()) emotions.add(getSong(songId));
		
		trendChartDto.setEmotions(emotions);
		
		return trendChartDto;
	}
	
	@Override
	public AgeSexChartDto getAgeSexChart(LocalDate date, String age, String sex) {
		return trendRepository.getAgeSexChart(date, age, sex);
	}

	@Override
	public SongArtistDetailDto getGenreChart(LocalDate date, String genre) {
		SongArtistDto dto = trendRepository.getGenreChart(date, genre);
		SongArtistDetailDto detailDto = new SongArtistDetailDto();
		List<TrendSongDto> songList = new ArrayList<>();
		
		for (long songId : dto.getSongs()) songList.add(getSong(songId));
		
		detailDto.setSongs(songList);
		
		return detailDto;
	}

	@Override
	public SongArtistDetailDto getAtmosphereChart(LocalDate date, String atmosphere) {
		SongArtistDto dto = trendRepository.getAtmosphereChart(date, atmosphere);
		SongArtistDetailDto detailDto = new SongArtistDetailDto();
		List<TrendSongDto> songList = new ArrayList<>();
		
		for (long songId : dto.getSongs()) songList.add(getSong(songId));
		
		detailDto.setSongs(songList);
		
		return detailDto;
	}

	@Override
	public BpmChartDto getBpmChart(LocalDate date, String bpm) {
		return trendRepository.getBpmChart(date, bpm);
	}

}
