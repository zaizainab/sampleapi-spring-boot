package com.ecomindo.onboarding.sampleapi.redis.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "Movies", timeToLive = 3000)
public class MoviesRedisModel {
	private Long id;
	private String name;
	private String genre;
	
	public MoviesRedisModel() {
		
	}
	
	public MoviesRedisModel(Long id, String name, String genre) {
		this.id = id;
		this.name = name;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}	
	
}
