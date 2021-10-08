package com.ecomindo.onboarding.sampleapi.controller.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.services.scheduler.InsertMoviesService;

@CrossOrigin(methods = { RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/movies/scheduler")
public class InsertMoviesJobController {
	private final InsertMoviesService service;
	private static final Logger logger = LoggerFactory.getLogger(InsertMoviesJobController.class);

	@Autowired
    public InsertMoviesJobController(InsertMoviesService service) {
        this.service = service;
    }

	@PostMapping("/insert")
	public void runInsertMovies() {
		try {
			service.runInsertMoviesJob();
		} catch (Exception e) {
			logger.error("Error scheduling", e);
		}		
	}
}
