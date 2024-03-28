package com.ssafy.singsongsangsong.repository.maria.atmosphere;

import java.util.List;
import java.util.Optional;

import com.ssafy.singsongsangsong.entity.Atmosphere;

public interface AtmosphereRepositoryCustom {
	List<Atmosphere> getAtmosphereBySongId(Long songId);

	Optional<Atmosphere> getFirstAtmosphereBySongId(Long songId);
}
