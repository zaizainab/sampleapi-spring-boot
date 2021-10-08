package com.ecomindo.onboarding.sampleapi.redis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.redis.dao.MovieRedisDao;
import com.ecomindo.onboarding.sampleapi.redis.model.MoviesRedisModel;

@Service
public class MoviesRedisServiceImpl implements MoviesRedisService{

	@Autowired
	MovieRedisDao movieRedisDao;
	
	@Override
	public void addMovieToRedis(MoviesRedisModel movie) {
		movieRedisDao.save(movie);
	}

	@Override
	public List<MoviesRedisModel> getMovieByName(String name) {
		List<MoviesRedisModel> movie = movieRedisDao.findByName(name);
		if(movie != null)
			return null;
		
		return movie;
	}

	@Override
	public void updateMovieInRedis(MoviesRedisModel movie) {
		movieRedisDao.save(movie);
	}

	@Override
	public void deleteMovieInRedisByName(String name) {
		movieRedisDao.deleteByName(name);
	}

}
