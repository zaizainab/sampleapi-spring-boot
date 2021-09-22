package com.ecomindo.onboarding.sampleapi.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoviesDaoTest {

	@Autowired
	private MoviesDao moviesDao;
	
	@Test
	void findByName() {
		moviesDao.save(movieData());
		List<MoviesModel> movie = moviesDao.findByName(movieData().getName());
		assertThat(movie).isNotNull();
		assertThat(movie.get(0).getName()).isEqualTo(movieData().getName());
	}

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
}
