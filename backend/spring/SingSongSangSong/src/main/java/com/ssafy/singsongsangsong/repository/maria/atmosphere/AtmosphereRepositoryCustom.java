package com.ssafy.singsongsangsong.repository.maria.atmosphere;

import java.util.List;
import java.util.Optional;

import com.ssafy.singsongsangsong.entity.Atmosphere;

public interface AtmosphereRepositoryCustom {
	List<Atmosphere> getAtmosphereBySongId(Long songId);

	List<Atmosphere> findBySongId(Long songId, int limit);

	Optional<Atmosphere> getFirstAtmosphereBySongId(Long songId);
}
