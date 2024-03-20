package com.ssafy.singsongsangsong.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleJobConfiguration {
	
	@Bean
	public Job simpleJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("simpleJob", jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Step simpleStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("simpleStep", jobRepository)
				.tasklet((contribution, chunkContext) -> {
					System.out.println("12345\n12345\n");
					return RepeatStatus.FINISHED;
				}, platformTransactionManager)
				.build();
	}

}
