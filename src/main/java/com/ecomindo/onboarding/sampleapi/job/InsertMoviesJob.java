package com.ecomindo.onboarding.sampleapi.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomindo.onboarding.sampleapi.dto.TimerInfoDTO;
import com.ecomindo.onboarding.sampleapi.services.MoviesService;

@Component
public class InsertMoviesJob implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(InsertMoviesJob.class);
	
	@Autowired
	MoviesService moviesService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfoDTO info = (TimerInfoDTO) jobDataMap.get(InsertMoviesJob.class.getSimpleName());
        
        String formattedDate = new SimpleDateFormat("yyyyMMdd_hh:mm").format(new Date());
        String name = String.format("Test movie %s", formattedDate);
		String description = String.format("Test description %s", formattedDate);
		String genre = "Test Genre";
		String username = "Test User";
		
		info.setCallbackData(String.format("movie name: %s", name));
		
        moviesService.insert(name, description, genre, username);
        
        LOG.info("Successfully Insert '{}'", info.getCallbackData());		
	}	
}
