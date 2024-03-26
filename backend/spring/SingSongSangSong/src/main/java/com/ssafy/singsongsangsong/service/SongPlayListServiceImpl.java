package com.ssafy.singsongsangsong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.HotArtistDto;
import com.ssafy.singsongsangsong.dto.HotArtistResponseDto;
import com.ssafy.singsongsangsong.dto.LikedPageResponseDto;
import com.ssafy.singsongsangsong.dto.SongBriefDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Likes;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.repository.maria.liked.LikedRepository;
import com.ssafy.singsongsangsong.repository.maria.song.AtmosphereRepository;
import com.ssafy.singsongsangsong.repository.maria.song.GenreRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongPlayListServiceImpl implements SongPlayListService{
	private final SongRepository songRepository;
	private final LikedRepository likedRepository;
	private final ArtistRepository artistRepository;
	private final GenreRepository genreRepository;
	private final AtmosphereRepository atmosphereRepository;
	@Override
	public LikedPageResponseDto getLikedPagination(String username, int pageNo) {
		System.out.println(username);
		Artist artist = artistRepository.findByUsername(username).orElse(null);
		PageRequest pageRequest = PageRequest.of(pageNo-1,10);
		log.info("artistId : {}", artist.getId());
		List<SongBriefDto> likedSongList = songsFromLikes(likedRepository.getLikedSongsByArtistId(artist.getId()));
		log.info("likedSongList : {}",likedSongList);
		int start = (int)pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()),likedSongList.size());
		Page<SongBriefDto> likedSongPage = new PageImpl<>(likedSongList.subList(start,end),pageRequest,likedSongList.size());
		log.info("pageData : {}", likedSongPage.getContent());

		return LikedPageResponseDto.from(likedSongPage);
	}

	private List<SongBriefDto> songsFromLikes (List<Likes> likesList) {
		List<SongBriefDto> songsFromLikes = new ArrayList<>();
		for(Likes likes : likesList) {
			songsFromLikes.add(SongBriefDto.from(likes.getSong()));
		}
		return songsFromLikes;
	}

	@Override
	public List<SongBriefDto> getWeeklyHitSongList() {
		List<SongBriefDto> list = new ArrayList<>();
		List<Song> songListFromDB = songRepository.findSongOrderByWeeklyCountDesc();
		for(Song song : songListFromDB) {
			list.add(SongBriefDto.from(song));
		}
		return list;
	}

	@Override
	public List<SongBriefDto> getGenreHitSongList(String genre) {
		List<SongBriefDto> list = new ArrayList<>();
		List<Song> songListFromDB = songRepository.findSongForGenreOrderByWeeklyCountDesc(genre);
		for(Song song : songListFromDB) {
			list.add(SongBriefDto.from(song));
		}
		return list;
	}

	@Override
	public List<SongBriefDto> getAtmosphereHitSongList(String atmosphere) {
		List<SongBriefDto> list = new ArrayList<>();
		List<Song> songListFromDB = songRepository.findSongForAtmosphereOrderByWeeklyCountDesc(atmosphere);
		for(Song song : songListFromDB) {
			list.add(SongBriefDto.from(song));
		}
		return list;
	}

	@Override
	public List<ArtistInfoDto> getFollowArtistList(String username) {
		Artist artist = artistRepository.findByUsername(username)
			.orElseThrow(() -> new ArtistNotFoundException("유효하지 않은 user입니다."));
		List<ArtistInfoDto> list = new ArrayList<>();
		List<Artist> followArtistFromDB = artistRepository.getFollowArtistByArtistId(artist.getId());
		for(Artist followArtist : followArtistFromDB) {
			list.add(ArtistInfoDto.from(followArtist));
		}
		return list;
	}

	// @Override
	// public SearchResponseDto searchArtistAndSong(String keyword, String genre, String atmosphere, Integer bpm,
	// 	String sort) {
	// 	List<Song> resultSongs = new ArrayList<>();
	// 	int[] bpmRange = bpmRange(bpm);
	// 	List<OrderSpecifier> orderSpecifiers = sortStandard(sort);
	// 	List<Song> songListByBpm = songRepository.findSongByBpmAndKeyword(keyword, bpmRange[0],bpmRange[1],orderSpecifiers);
	// 	if(genre != null) {
	// 		// song에는 genre가 없어 그럼 genreRepo에서 mainCategory가 같고 correlation이 60 이상인
	// 		// genre row들을 그냥 뽑아와? 그럼 앞에 song 파이를 줄여준 의미가 뭐가 있지?
	// 	}
	// 	if(atmosphere != null)
	// 	List<Artist> artistList = artistRepository.findArtistBySearchParam(keyword);
	// 	return SearchResponseDto.from(artistList,resultSongs);
	// }

	@Override
	public List<HotArtistResponseDto> getHotArtist() {
		List<HotArtistDto> hotArtistResponseDtoList= artistRepository.findHotArtist();
		List<HotArtistResponseDto> result = new ArrayList<>();
		for(HotArtistDto hotArtistDto : hotArtistResponseDtoList) {
			HotArtistResponseDto hotArtistResponseDto = new HotArtistResponseDto();
			hotArtistResponseDto.setWeeklyPlayCountSum(hotArtistDto.getWeeklyPlayCountSum());
			hotArtistResponseDto.setArtistInfoDto(ArtistInfoDto.from(hotArtistDto.getArtist()));
			result.add(hotArtistResponseDto);
		}
		return result;
	}

	private int[] bpmRange(Integer bpm) {
		int startBpm = 0;
		int endBpm = 39;
		if(bpm >= 40 && bpm < 200) {
			startBpm = bpm;
			endBpm = bpm + 20;
		}
		else if(bpm >= 200) {
			startBpm = 200;
			endBpm = 10000;
		}
		return new int[] {startBpm,endBpm};
	}

	// private List<OrderSpecifier> sortStandard(String sort) {
	// 	List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
	// 	if(sort.equals("date")) {
	// 		orderSpecifiers.add(new OrderSpecifier(Order.DESC, song.createdDate));
	// 	}
	// 	if(sort.equals("view")) {
	// 		orderSpecifiers.add(new OrderSpecifier(Order.DESC, song.playCount));
	// 	}
	// 	else {
	// 		orderSpecifiers.add(new OrderSpecifier(Order.DESC, song.likeCount));
	// 	}
	// 	return orderSpecifiers;
	// }

}
