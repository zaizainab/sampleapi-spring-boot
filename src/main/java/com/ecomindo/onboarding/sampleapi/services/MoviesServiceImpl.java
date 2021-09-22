package com.ecomindo.onboarding.sampleapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dao.MoviesDao;
import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

@Service
public class MoviesServiceImpl implements MoviesService {
	
	@Autowired
	MoviesDao moviesDao;

	@Transactional
	@Override
	public MoviesModel insert(String name, String description, String genre, String username) {
		try {
//			Instant instant = Instant.now();
//			long currentDate = instant.toEpochMilli();
			
			MoviesModel movie = moviesDao.save(new MoviesModel(name, description, genre, username, null, username, null));
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional
	@Override
	public MoviesModel insert2(MoviesModel moviesModel) {
		try {
			
			MoviesModel movie = moviesDao.save(moviesModel);
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MoviesModel update(UpdateMovieDTO movieDto) {
		try {
			
			MoviesModel movie = moviesDao.findById(movieDto.getId()).orElseThrow();
			
			if(movie.getId() > 0) {
				movie.setName(movieDto.getName());
				movie.setDescription(movieDto.getDescription());
				movie.setGenre(movieDto.getGenre());
				movie.setLastUpdatedBy(movieDto.getLastUpdatedBy());
				
				moviesDao.save(movie);
			}
			
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean delete(long id) {

		if(!moviesDao.existsById(id))
			return false;
		
		moviesDao.deleteById(id);
		return true;
	}

	@Override
	public List<MoviesModel> getMovies() {

		return StreamSupport.stream(moviesDao.findAll().spliterator(), false)
//				.map(e-> Mapper.convertBookToBookDto(e, true, true, true))
				.collect(Collectors.toList());
	}

	@Override
	public List<MoviesModel> getMoviesByName(String name) {
		return StreamSupport.stream(moviesDao.findByName(name).spliterator(), false)
				.collect(Collectors.toList());
	}
	
}
