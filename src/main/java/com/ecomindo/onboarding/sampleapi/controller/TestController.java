package com.ecomindo.onboarding.sampleapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
public class TestController {
	
    @RequestMapping(value = "/hello", method=RequestMethod.POST)
    public ResponseDTO helloWorld(@RequestBody String name) {
    	ResponseDTO response = new ResponseDTO();
    	
    	response.setCode("200");
    	response.setMessage(HttpStatus.OK.toString());
    	response.setData(String.format("Hello %s welcome", name));
    	return response;
    }

    @RequestMapping(value = "/hello-world", method=RequestMethod.GET)
    public ResponseDTO helloWorld() {
    	ResponseDTO response = new ResponseDTO();
    	
    	response.setCode("200");
    	response.setMessage("Hello");
    	
    	return response;
    }
}
