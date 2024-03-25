package com.ssafy.singsongsangsong.repository.maria.atmosphere;

import java.util.List;

import com.ssafy.singsongsangsong.entity.Atmosphere;

public interface AtmosphereRepositoryCustom {
	public List<Atmosphere> getAtmosphereBySongId(Long songId);
}
