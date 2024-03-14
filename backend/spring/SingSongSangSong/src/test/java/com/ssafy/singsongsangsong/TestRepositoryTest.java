package com.ssafy.singsongsangsong;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.singsongsangsong.domain.TestEntity;
import com.ssafy.singsongsangsong.repository.TestRepository;

@SpringBootTest
public class TestRepositoryTest {

	@Autowired
	private TestRepository testRepository;

	@Test
	public void queryDSLTest() {
		String value = "this is test";
		testRepository.save(new TestEntity(value));

		List<TestEntity> result = testRepository.customFindByValue(value);

		assertThat(result.size()).isEqualTo(1);
		assertThat(result.getFirst().getValue()).isEqualTo(value);
	}

}
