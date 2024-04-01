package com.ssafy.singsongsangsong.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.ssafy.singsongsangsong.dto.AtmosphereCountDto;
import com.ssafy.singsongsangsong.dto.GenreCountDto;

public class MyJobListener implements JobListener {
	
	@Override
	public String getName() {
		return "myJob";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
		String[] ages = {"10", "20", "30", "40", "50"};
		String[] sexs = {"M", "F"};
		
		for (int i = 0; i < ages.length; i++) {
			for (int j = 0; j < sexs.length; j++) {
				String ageSex = ages[i].concat(sexs[j]);
				
				if (jobExecutionContext.get(ageSex.concat("/g")) == null) jobExecutionContext.put(ageSex.concat("/g"), new GenreCountDto());
				if (jobExecutionContext.get(ageSex.concat("/a")) == null) jobExecutionContext.put(ageSex.concat("/a"), new AtmosphereCountDto());
			}
		}
		
		System.out.println("Job 실행 전");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("Job 실행 취소");
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		System.out.println("Job 실행 완료");
	}
}
