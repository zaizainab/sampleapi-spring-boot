package com.ecomindo.onboarding.sampleapi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesControllerTest {

	@LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void test_insert() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	MoviesModel request = new MoviesModel("test", "test description", "test genre", "test user", null, "test user", null);
    	
    	ResponseDTO result = this.restTemplate.postForObject(url, request, ResponseDTO.class);
    	assertThat(result.getCode().equals("200"));
    }
    
    @Test
    public void test_insert2() {
    	url = String.format("http://localhost:%d/movies/insert", port);
    	NewMoviesDTO request = new NewMoviesDTO("test2", "test description", "test genre", "test user");
    	
    	ResponseDTO result = this.restTemplate.postForObject(url, request, ResponseDTO.class);
    	assertThat(result.getCode().equals("200"));
    }
    
    @Test
    public void test_update() {
    	url = String.format("http://localhost:%d/movies/update", port);
    	UpdateMovieDTO request = new UpdateMovieDTO(1, "test name", "test description", "test genre", "test user");
    	
    	ResponseDTO result = this.restTemplate.postForObject(url, request, ResponseDTO.class);
    	assertThat(result.getCode().equals("200"));
    }
    
    @Test
    public void test_delete() {
    	long id = 1;
    	url = String.format("http://localhost:%d/movies/delete/%d", port, id);    	
    	
    	restTemplate.delete(url);
    }
}
