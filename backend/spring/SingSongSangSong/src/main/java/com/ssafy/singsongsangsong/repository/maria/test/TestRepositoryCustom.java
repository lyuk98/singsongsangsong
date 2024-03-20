package com.ssafy.singsongsangsong.repository.maria.test;

import java.util.List;

import com.ssafy.singsongsangsong.entity.TestEntity;

public interface TestRepositoryCustom {
	List<TestEntity> customFindByAbc(String abc);
}
