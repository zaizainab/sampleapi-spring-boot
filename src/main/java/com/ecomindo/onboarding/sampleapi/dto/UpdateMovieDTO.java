package com.ecomindo.onboarding.sampleapi.dto;

public class UpdateMovieDTO {

	private long id;
	private String name;
	private String description;
	private String genre;
	private String lastUpdatedBy;
	
	public UpdateMovieDTO() {
		
	}
	
	public UpdateMovieDTO(long id, String name, String description, String genre, String lastUpdatedBy) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
