package com.ecomindo.onboarding.sampleapi.services;

import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;

import java.util.List;

import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

public interface MoviesService {

	public MoviesModel insert(String name, String description, String genre, String username);
	public MoviesModel update(UpdateMovieDTO movieDto);
	public Boolean delete(long id);
	public List<MoviesModel> getMovies();
	public List<MoviesModel> getMoviesByName(String name);
	public MoviesModel insert2(MoviesModel moviesModel);
}
