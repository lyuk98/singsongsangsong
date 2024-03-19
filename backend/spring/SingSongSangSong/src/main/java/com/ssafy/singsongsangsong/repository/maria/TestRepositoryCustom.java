package com.ssafy.singsongsangsong.repository.maria;

import java.util.List;

import com.ssafy.singsongsangsong.domain.TestEntity;

public interface TestRepositoryCustom {
	List<TestEntity> customFindByAbc(String abc);
}
