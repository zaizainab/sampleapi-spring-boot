package com.ecomindo.onboarding.sampleapi.controller;

import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.kafka.service.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	KafkaProducer kafkaProducer;
	
    @RequestMapping(value = "/sendMessage", method=RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody NewMoviesDTO dto) {
    	try {
            ObjectMapper mapper = new ObjectMapper();
            String stringified = mapper.writeValueAsString(dto);

			kafkaProducer.sendMessage(stringified);

            String msg = "Success sending message to kafka";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
