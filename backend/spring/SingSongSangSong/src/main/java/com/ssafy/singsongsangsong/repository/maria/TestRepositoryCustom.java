package com.ssafy.singsongsangsong.repository.maria;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.domain.TestEntity;

public interface TestRepositoryCustom {
	List<TestEntity> customFindByValue(String value);
}
