package com.ecomindo.onboarding.sampleapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

@Repository
public interface MoviesDao extends JpaRepository<MoviesModel, Long> {

	List<MoviesModel> findByGenre(String genre);
	
	@Query("SELECT a FROM MoviesModel a WHERE a.name like CONCAT('%', :name ,'%')")
	List<MoviesModel> findByName(String name);
		
//	@Query("Select new com.ecomindo.onboarding.sampleapi.dto.MoviesDTO from MoviesModel m limit 1 order by id desc limit 1")
//	List<MoviesModel> findLastAddedMovies();
}
