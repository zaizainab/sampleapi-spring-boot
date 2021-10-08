package com.ecomindo.onboarding.sampleapi.redis.service;

import java.util.List;

import com.ecomindo.onboarding.sampleapi.redis.model.MoviesRedisModel;

public interface MoviesRedisService {

	void addMovieToRedis(MoviesRedisModel movie);
	List<MoviesRedisModel> getMovieByName(String name);
    void updateMovieInRedis(MoviesRedisModel movie);
    void deleteMovieInRedisByName(String name);
}
