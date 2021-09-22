package com.ecomindo.onboarding.sampleapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecomindo.onboarding.sampleapi.dao.MoviesDao;
import com.ecomindo.onboarding.sampleapi.dto.OptionsDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.BooksModel;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;
import com.ecomindo.onboarding.sampleapi.services.MoviesService;
import com.ecomindo.onboarding.sampleapi.services.MoviesServiceImpl;

@SpringBootTest
public class MoviesControllerMockitoTest {

	@Mock
	MoviesDao moviesDao;
	
	@InjectMocks
	MoviesService moviesService = new MoviesServiceImpl();
	
//	@Autowired
//    private TestEntityManager entityManager;
	
//	@BeforeEach
//    public void init() {
//    	
//        entityManager.persist(movieData());
//        
//        entityManager.flush();
//    }
	
	@DisplayName("Test Mock insert")
	@Test
	void testInsert() {
		when(moviesDao.save(any())).thenReturn(movieData());
		MoviesModel createdMovie = moviesService.insert2(movieData());
		
		assertThat(createdMovie).isNotNull();
		assertThat(createdMovie.getName()).isEqualTo(movieData().getName());
	}
	
//	@DisplayName("Test Mock update")
//	@Test
//	void testUpdate() {
//		when(moviesDao.save(any())).thenReturn(movieData());
//		MoviesModel updatedMovie = moviesService.update(updateMovieData());
//		
//		assertThat(updatedMovie).isNotNull();
//		assertThat(updatedMovie.getName()).isEqualTo(updateMovieData().getName());
//	}
	
//	@DisplayName("Test Mock delete")
//	@Test
//	void testDelete() {
//		when(moviesDao.save(any())).thenReturn(movieData());
//		Boolean isDeleted = moviesService.delete(movieData().getId());
//		
//		assertThat(isDeleted).isNotNull();
//		assertThat(isDeleted).isEqualTo(true);
//	}
	
	private static MoviesModel movieData() {
		Date moment = Date.from(Instant.now());		
		MoviesModel m = new MoviesModel();
		m.setName("test name");
		m.setDescription("test description");
		m.setGenre("test genre");
		m.setCreatedBy("test user");
		m.setCreatedDate(moment);
		m.setLastUpdatedBy(null);
		m.setLastUpdatedDate(null);
		
		return m;
	}
	
	private static UpdateMovieDTO updateMovieData() {
		UpdateMovieDTO m = new UpdateMovieDTO();
		m.setId(1);
		m.setName("test name update");
		m.setDescription("test description2");
		m.setGenre("test genre2");
		m.setLastUpdatedBy(null);
		
		return m;
	}
}
