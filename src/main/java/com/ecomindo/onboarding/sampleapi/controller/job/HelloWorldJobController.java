package com.ecomindo.onboarding.sampleapi.controller.job;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.services.scheduler.HelloWorldService;

@CrossOrigin(methods = { RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/scheduler")
public class HelloWorldJobController {	
	private final HelloWorldService service;
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldJobController.class);

	@Autowired
    public HelloWorldJobController(HelloWorldService service) {
        this.service = service;
    }

	@PostMapping("/helloworld")
	public void runHelloWorld() {
		try {
			service.runHelloWorldJob();
		} catch (Exception e) {
			logger.error("Error scheduling", e);
		}		
	}
	
}
