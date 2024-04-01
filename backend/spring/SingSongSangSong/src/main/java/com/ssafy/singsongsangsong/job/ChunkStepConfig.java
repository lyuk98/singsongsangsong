package com.ssafy.singsongsangsong.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.ssafy.singsongsangsong.dto.AtmosphereCountDto;
import com.ssafy.singsongsangsong.dto.GenreCountDto;
import com.ssafy.singsongsangsong.entity.Play;

@Configuration
public class ChunkStepConfig {
	
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
	@StepScope
	public FlatFileItemReader<Play> csvFileReader() {
		FlatFileItemReader<Play> itemReader = new FlatFileItemReader<>();
		itemReader.setEncoding("UTF-8");
		itemReader.setResource(new FileSystemResource("resources/players.csv"));
		itemReader.setLinesToSkip(1);
		
		DefaultLineMapper<Play> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new PlayFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		
		return itemReader;
	}
	
	@Bean
	@StepScope
	public StepExecutionListener stepExecutionListener() {
	    return new StepExecutionListener() {
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
//	            log.info("[StepExecutionListener#afterStep] stepExecution is " + stepExecution.getStatus());
	            return stepExecution.getExitStatus();
	        }
	    };
	}
	
//	@Bean
//	public MongoItemWriter<Play> mongoItemWriter(MongoOperations mongoTemplate) {
//		return new MongoItemWriterBuilder<Play>()
//				.collection("trend")
//				.template(mongoTemplate)
//				.build();
//	}
	
	@Bean
	public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		Step step = new StepBuilder("chunckStep", jobRepository)
				.listener(stepExecutionListener())
				.<Play, Play>chunk(10, transactionManager)
				.reader(csvFileReader())
				.processor(new PlayProcessor())
				.build();
		
		return step;
	}

}
