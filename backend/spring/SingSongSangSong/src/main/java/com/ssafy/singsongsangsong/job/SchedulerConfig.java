package com.ssafy.singsongsangsong.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SchedulerConfig {
	
	private Scheduler scheduler;
	
	public SchedulerConfig(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@PostConstruct
	private void jobProgress() throws SchedulerException {
		cronScheduler();
	}
	
	private void cronScheduler() throws SchedulerException {
		JobDetail job = JobBuilder
				.newJob(MyJob.class)
				.withIdentity("myJob", "myGroup")
				.build();
		
		CronTrigger cronTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "triggerGroup")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0 35 11 * * ?"))
				.build();
		
		scheduler = new StdSchedulerFactory().getScheduler();
		MyJobListener myJobListner = new MyJobListener();
		scheduler.getListenerManager().addJobListener(myJobListner);
		scheduler.start();
		scheduler.scheduleJob(job, cronTrigger);
	}

}
