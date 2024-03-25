package com.ssafy.singsongsangsong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.LikedPageResponseDto;
import com.ssafy.singsongsangsong.dto.SearchResponseDto;
import com.ssafy.singsongsangsong.dto.SongBriefDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Likes;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.repository.maria.liked.LikedRepository;
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

	@Override
	public SearchResponseDto searchArtistAndSong(String keyword, String genre, String atmosphere, int bpm,
		String sort) {
		List<Song> songList = songRepository.findSongBySearchParam(keyword, genre, atmosphere, bpm, sort);
		List<Artist> artistList = artistRepository.findArtistBySearchParam(keyword);
		return SearchResponseDto.from(artistList,songList);
	}

}
