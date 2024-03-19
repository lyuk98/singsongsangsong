package com.ssafy.singsongsangsong.repository.maria.test;

import static com.ssafy.singsongsangsong.entity.QTestEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.entity.TestEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TestRepositoryCustomImpl implements TestRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<TestEntity> customFindByAbc(String abc) {
		return jpaQueryFactory.selectFrom(testEntity).where(testEntity.abc.eq(abc)).fetch();
	}
}
