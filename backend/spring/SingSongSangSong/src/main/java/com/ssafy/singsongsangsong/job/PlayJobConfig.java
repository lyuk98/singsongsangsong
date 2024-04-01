package com.ssafy.singsongsangsong.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.ssafy.singsongsangsong.dto.AtmosphereCountDto;
import com.ssafy.singsongsangsong.dto.GenreCountDto;
import com.ssafy.singsongsangsong.entity.Play;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class PlayJobConfig {
	
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	@Bean
	public Job playJob() {
		return new JobBuilder("playJob", jobRepository)
				.start(jobStep())
				.preventRestart()
				.incrementer(new MyJobParametersIncrementer())
				.build();
	}
	
	@Bean
	public Step jobStep() {
		Step step = new StepBuilder("jobStep", jobRepository)
				.listener(stepExecutionListener())
				.<Play, Play>chunk(10, transactionManager)
				.reader(csvFileReader())
				.processor(new PlayProcessor())
				.writer(new PlayWriter())
				.build();
		
		return step;
	}
	
	protected static class PlayFieldSetMapper implements FieldSetMapper<Play> {
	    public Play mapFieldSet(FieldSet fieldSet) {
	        Play play = Play.builder()
	        		.songId(fieldSet.readLong("songId"))
	        		.genre(fieldSet.readString("genre"))
	        		.atmosphere(fieldSet.readString("atmosphere"))
	        		.age(fieldSet.readString("age"))
	        		.sex(fieldSet.readString("sex"))
	        		.build();

	        return play;
	    }
	}
	
	@Bean
//	@StepScope
	public FlatFileItemReader<Play> csvFileReader() {
		FlatFileItemReader<Play> itemReader = new FlatFileItemReader<>();
		itemReader.setEncoding("UTF-8");
		itemReader.setResource(new FileSystemResource("src/main/resources/log_dummy.csv"));
		itemReader.setLinesToSkip(1);
		
		DefaultLineMapper<Play> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new PlayFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		
		return itemReader;
	}
	
	@Bean
//	@StepScope
	public StepExecutionListener stepExecutionListener() {
	    return new StepExecutionListener() {
	    	@Autowired
	        private MongoTemplate mongoTemplate;
	    	
	        @Override
	        public void beforeStep(StepExecution stepExecution) {
	        	ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();
	        	
	        	String[] ages = {"10", "20", "30", "40", "50"};
	    		String[] sexs = {"M", "F"};
	    		
	    		for (int i = 0; i < ages.length; i++) {
	    			for (int j = 0; j < sexs.length; j++) {
	    				String ageSex = ages[i].concat(sexs[j]);
	    				
	    				if (stepExecutionContext.get(ageSex.concat("/g")) == null) stepExecutionContext.put(ageSex.concat("/g"), new GenreCountDto());
	    				if (stepExecutionContext.get(ageSex.concat("/a")) == null) stepExecutionContext.put(ageSex.concat("/a"), new AtmosphereCountDto());
	    			}
	    		}
	    		
	    		System.out.println("Job 실행 전");
	        }

	        @Override
	        public ExitStatus afterStep(StepExecution stepExecution) {
	        	mongoTemplate.save(stepExecution.getExecutionContext().get("10M/g"));
	        	
	            return stepExecution.getExitStatus();
	        }
	    };
	}

}
