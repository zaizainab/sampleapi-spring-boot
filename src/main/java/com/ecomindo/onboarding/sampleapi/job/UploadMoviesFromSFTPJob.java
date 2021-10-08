package com.ecomindo.onboarding.sampleapi.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecomindo.onboarding.sampleapi.config.Config;
import com.ecomindo.onboarding.sampleapi.dto.TimerInfoDTO;
import com.ecomindo.onboarding.sampleapi.services.FileService;
import com.ecomindo.onboarding.sampleapi.services.MoviesService;

@Component
public class UploadMoviesFromSFTPJob implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(UploadMoviesFromSFTPJob.class);
	
//	@Autowired
//	Config config;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MoviesService moviesService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfoDTO info = (TimerInfoDTO) jobDataMap.get(UploadMoviesFromSFTPJob.class.getSimpleName());    
        
        LOG.info(info.getCallbackData());
		
        try {
        	List<String> fileContent = fileService.getFileContentByFilename("MOV001.txt");
    		
    		if(fileContent.isEmpty())
    			throw new Exception("There is no such file");
    		
    		fileContent.remove(0); //header
    		
    		List<String> firstBatchValue = new ArrayList<>(fileContent.subList(0, (fileContent.size())/2));
    		List<String> secondBatchValue = new ArrayList<>(fileContent.subList((fileContent.size())/2, fileContent.size()));
    		Future<Void> firstBatch = moviesService.upload(firstBatchValue);
    		Future<Void> secondBatch = moviesService.upload(secondBatchValue);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
        LOG.info("Finish Upload Movie");	
	}
}
