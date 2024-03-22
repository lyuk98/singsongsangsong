package com.ssafy.singsongsangsong.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
	
	@Override
	public String getName() {
		return "myJob";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
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
