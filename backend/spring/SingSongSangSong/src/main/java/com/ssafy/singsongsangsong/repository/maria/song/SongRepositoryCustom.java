package com.ssafy.singsongsangsong.repository.maria.song;

import java.util.List;

import com.ssafy.singsongsangsong.entity.Song;

public interface SongRepositoryCustom {
	public List<Song> getPublishedSongsByArtistId(Long artistId);
}
