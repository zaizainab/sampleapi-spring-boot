package com.ecomindo.onboarding.sampleapi.services.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dto.TimerInfoDTO;
import com.ecomindo.onboarding.sampleapi.job.HelloWorldJob;

@Service
public class HelloWorldService {

	private final SchedulerService scheduler;

    @Autowired
    public HelloWorldService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorldJob() {
        final TimerInfoDTO info = new TimerInfoDTO();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(5000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("My callback data");

        scheduler.schedule(HelloWorldJob.class, info);
    }

//    public Boolean deleteTimer(final String timerId) {
//        return scheduler.deleteTimer(timerId);
//    }
//
//    public List<TimerInfo> getAllRunningTimers() {
//        return scheduler.getAllRunningTimers();
//    }
//
//    public TimerInfo getRunningTimer(final String timerId) {
//        return scheduler.getRunningTimer(timerId);
//    }
}
