package com.ssafy.singsongsangsong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.singsongsangsong.dto.ArtistInfoDto;
import com.ssafy.singsongsangsong.dto.EmotionsDto;
import com.ssafy.singsongsangsong.dto.FollowerCountResponse;
import com.ssafy.singsongsangsong.dto.MyProfileResponse;
import com.ssafy.singsongsangsong.dto.SimpleSongDto;
import com.ssafy.singsongsangsong.entity.Artist;
import com.ssafy.singsongsangsong.entity.Follower_Following;
import com.ssafy.singsongsangsong.entity.Song;
import com.ssafy.singsongsangsong.exception.artist.ArtistNotFoundException;
import com.ssafy.singsongsangsong.repository.maria.artist.ArtistRepository;
import com.ssafy.singsongsangsong.repository.maria.artist.FollowingRepository;
import com.ssafy.singsongsangsong.repository.maria.song.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
	private final ArtistRepository artistRepository;
	private final SongRepository songRepository;
	private final FollowingRepository followingRepository;

	@Override
	public ArtistInfoDto getArtistInfo(Long artistId) {
		Artist artist = artistRepository.findById(artistId)
			.orElseThrow(() -> new IllegalArgumentException("해당 아티스트가 존재하지 않습니다."));
		return ArtistInfoDto.from(artist);
	}

	@Override
	public List<SimpleSongDto> getPublishedSong(Long artistId) {
		return artistRepository.getPublishedSongsByArtistId(artistId)
			.stream()
			.map(SimpleSongDto::from)
			.toList();
	}

	@Override
	public void toggleFollowArtist(String username, Long followingId) throws ArtistNotFoundException {
		Artist follower = artistRepository.findByUsername(username)
			.orElseThrow(() -> new ArtistNotFoundException("해당 유저가 존재하지 않습니다."));

		Artist following = artistRepository.findById(followingId)
			.orElseThrow(() -> new ArtistNotFoundException("해당 아티스트가 존재하지 않습니다."));

		// 기존에 좋아요를 눌렀다면, 좋아요를 취소합니다.
		followingRepository.getFollowingWhere(follower.getId(), followingId)
			.ifPresentOrElse(
				followingRepository::delete,
				() -> followingRepository.save(
					Follower_Following.of(follower, following)));
	}

	@Override
	public EmotionsDto getEmotions(Long artistId) {
		List<Song> publishedSongs = songRepository.getPublishedSongsByArtistId(artistId);
		EmotionsDto result = new EmotionsDto();
		for (Song song : publishedSongs) {
			result.setEnergizedEmotionCount(result.getEnergizedEmotionCount() + song.getEnergizedEmotionCount());
			result.setExcitedEmotionCount(result.getExcitedEmotionCount() + song.getExcitedEmotionCount());
			result.setFunnyEmotionCount(result.getFunnyEmotionCount() + song.getFunnyEmotionCount());
			result.setSadEmotionCount(result.getSadEmotionCount() + song.getSadEmotionCount());
			result.setMovedEmotionCount(result.getMovedEmotionCount() + song.getMovedEmotionCount());
			result.setLikeEmotionCount(result.getLikeEmotionCount() + song.getLikeEmotionCount());
		}
		return result;
	}

	@Override
	public FollowerCountResponse getFollowerCount(Long artistId) {
		int followerCount = artistRepository.getFollowerCountByArtistId(artistId);
		return FollowerCountResponse.builder()
			.artistId(artistId)
			.followerCount(followerCount)
			.build();
	}

	@Override
	public MyProfileResponse getMyProfile(Long id) {
		Artist artist = artistRepository.findById(id).orElseThrow(ArtistNotFoundException::new);
		return MyProfileResponse.builder()
			.id(id)
			.username(artist.getUsername())
			.nickname(artist.getNickname())
			.build();
	}
}
