package com.ecomindo.onboarding.sampleapi.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quartz {
//	@Bean
//	public JobDetail jobHelloWorld() {
//		return JobBuilder.newJob(HelloWorldJob.class).withIdentity("JobHelloWorld", "insert-book-second")
//				.storeDurably()
//				.build();
//	}

//	@Bean
//	public Trigger jobAutoInsertTrigger(JobDetail jobAutoInsertDetails) {
//		return TriggerBuilder.newTrigger().forJob(jobAutoInsertDetails)
//				.withIdentity("trigger-from-main", "insert-book-second")
//				.startNow()
//				.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * ? * * *")).build();
//	}
	
}
