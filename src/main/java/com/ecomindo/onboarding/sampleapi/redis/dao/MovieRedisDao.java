package com.ecomindo.onboarding.sampleapi.redis.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecomindo.onboarding.sampleapi.redis.model.MoviesRedisModel;

@Repository
public interface MovieRedisDao extends CrudRepository<MoviesRedisModel, Long> {

	List<MoviesRedisModel> findByName(String name);
	void deleteByName(String name);
}
