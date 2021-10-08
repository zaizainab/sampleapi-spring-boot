package com.ecomindo.onboarding.sampleapi.kafka.service;

import com.ecomindo.onboarding.sampleapi.config.Config;
import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.services.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    Config config;

    @Autowired
    MoviesService moviesService;

	@KafkaListener(topics = "#{config.getKafkaTopic()}", groupId = "#{config.getKafkaGroupId()}")
	public void listToTopicMovies(String message) {
		System.out.println("Received Message in group movie-listener: " + message);

        try{
            ObjectMapper mapper = new ObjectMapper();
            NewMoviesDTO dto = mapper.readValue(message, NewMoviesDTO.class);

            moviesService.insert(dto.getName(), dto.getDescription(), dto.getGenre(), "redis");
        }catch(Exception e){
            System.out.println("Error processing data from kafka message : " + e.getMessage());
        }
        
	}

}
