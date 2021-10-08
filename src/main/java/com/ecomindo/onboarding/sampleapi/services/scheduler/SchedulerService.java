package com.ecomindo.onboarding.sampleapi.services.scheduler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dto.TimerInfoDTO;
import com.ecomindo.onboarding.sampleapi.util.JobUtil;

@Service
public class SchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);
    private final Scheduler scheduler;
    
    @Autowired
    public SchedulerService(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public <T extends Job> void schedule(final Class<T> jobClass, final TimerInfoDTO info) {
        final JobDetail jobDetail = JobUtil.buildJobDetail(jobClass, info);
        final Trigger trigger = JobUtil.buildTrigger(jobClass, info);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
//    @PostConstruct
//    public void init() {
//        try {
//            scheduler.start();
////            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this));
//        } catch (SchedulerException e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }
//
//    @PreDestroy
//    public void preDestroy() {
//        try {
//            scheduler.shutdown();
//        } catch (SchedulerException e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }
}
