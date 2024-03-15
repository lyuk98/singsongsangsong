package com.ssafy.singsongsangsong.repository.maria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.singsongsangsong.domain.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>, TestRepositoryCustom {

}
