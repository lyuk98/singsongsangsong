package com.ssafy.singsongsangsong.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.domain.TestEntity;

public interface TestRepositoryCustom {
	List<TestEntity> customFindByValue(String value);
}
