package com.ssafy.singsongsangsong.repository.maria.atmosphere;

import java.util.List;

import com.ssafy.singsongsangsong.entity.Atmosphere;
import com.ssafy.singsongsangsong.entity.Song;

public interface AtmosphereRepositoryCustom {
	public List<Atmosphere> getAtmosphereBySongId(Long songId);
	List<Song> atmosphereFilterList(List<Long> songIdList,String requestAtmosphere);
}
