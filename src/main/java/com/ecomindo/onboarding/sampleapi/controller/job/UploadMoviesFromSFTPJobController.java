package com.ecomindo.onboarding.sampleapi.controller.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.services.scheduler.UploadMoviesFromSFTPService;

@CrossOrigin(methods = { RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/scheduler")
public class UploadMoviesFromSFTPJobController {

	private final UploadMoviesFromSFTPService service;
	private static final Logger logger = LoggerFactory.getLogger(UploadMoviesFromSFTPJobController.class);

	@Autowired
    public UploadMoviesFromSFTPJobController(UploadMoviesFromSFTPService service) {
        this.service = service;
    }

	@PostMapping("/uploadMovie")
	public void runInsertMovies() {
		try {
			service.runUploadMoviesFromSFTPJob();
		} catch (Exception e) {
			logger.error("Error scheduling", e);
		}		
	}
}
