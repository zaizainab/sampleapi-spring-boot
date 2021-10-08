package com.ecomindo.onboarding.sampleapi.services.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dto.TimerInfoDTO;
import com.ecomindo.onboarding.sampleapi.job.UploadMoviesFromSFTPJob;

@Service
public class UploadMoviesFromSFTPService {

	private final SchedulerService scheduler;

    @Autowired
    public UploadMoviesFromSFTPService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runUploadMoviesFromSFTPJob() {
        final TimerInfoDTO info = new TimerInfoDTO();
        info.setTotalFireCount(1);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(600000);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("Uploading...");

        scheduler.schedule(UploadMoviesFromSFTPJob.class, info);
    }
}
