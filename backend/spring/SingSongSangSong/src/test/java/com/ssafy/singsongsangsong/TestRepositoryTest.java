package com.ssafy.singsongsangsong;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.singsongsangsong.entity.TestEntity;
import com.ssafy.singsongsangsong.repository.maria.test.TestRepository;

@SpringBootTest
public class TestRepositoryTest {

	@Autowired
	private TestRepository testRepository;

	@Nested
	class Describe_Create {
		// context
		@Nested
		class Context_유효한_입력 {
			String value = "this is test";

			@Test
			void It_테스트_엔티티가_생성된다() {
				TestEntity saved = testRepository.save(TestEntity.builder().abc(value).build());
				List<TestEntity> testEntities = testRepository.customFindByAbc(value);
				assertThat(testEntities.size()).isEqualTo(1);
				assertThat(testEntities.getFirst()).isEqualTo(saved);
			}
		}
	}

}
