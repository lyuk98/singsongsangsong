package com.ssafy.singsongsangsong.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class MyJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) {
		System.out.println("Freaky Deaky");
	}
}
