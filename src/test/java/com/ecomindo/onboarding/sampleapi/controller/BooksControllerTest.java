package com.ecomindo.onboarding.sampleapi.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.model.BooksModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksControllerTest {

	@LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;
        
    @Test
    public void test_insert() {
    	url = String.format("http://localhost:%d/books/insert", port);
    	BooksModel request = new BooksModel("test junit","test description junit", "test author junit");
    	
    	ResponseDTO result = this.restTemplate.postForObject(url, request, ResponseDTO.class);
    	assertThat(result.getCode().equals("200"));
    }
    
}
