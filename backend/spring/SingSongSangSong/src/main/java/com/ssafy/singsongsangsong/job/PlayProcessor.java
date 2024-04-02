package com.ssafy.singsongsangsong.job;

<<<<<<< HEAD
import org.springframework.batch.item.ItemProcessor;

import com.ssafy.singsongsangsong.entity.Play;

public class PlayProcessor implements ItemProcessor<Play, Play> {

	@Override
	public Play process(Play item) throws Exception {
		// TODO Auto-generated method stub
		return null;
=======
import java.util.TreeMap;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import com.ssafy.singsongsangsong.dto.GenreCountDto;
import com.ssafy.singsongsangsong.entity.Play;

public class PlayProcessor implements ItemProcessor<Play, Play> {
	
	private StepExecution stepExecution;
	
	@BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

	@Override
	public Play process(Play item) throws Exception {
		TreeMap<String, Long> genreCount = ((GenreCountDto) stepExecution.getExecutionContext().get("genre")).getGenreCount();
		genreCount.replace(item.getGenre(), genreCount.get(item.getGenre()) + 1);
		
		return item;
>>>>>>> c3657287620771bbbd843b6c4f5d534a498ffc7f
	}

}
