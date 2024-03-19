package com.ssafy.singsongsangsong.repository.maria.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>, TestRepositoryCustom {

}
