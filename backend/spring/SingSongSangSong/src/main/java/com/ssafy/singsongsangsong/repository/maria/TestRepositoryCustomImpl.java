package com.ssafy.singsongsangsong.repository.maria;

import static com.ssafy.singsongsangsong.domain.QTestEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.singsongsangsong.domain.TestEntity;

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
