package com.ecomindo.onboarding.sampleapi.dto;

public class NewMoviesDTO {

	private String name;
	private String description;
	private String genre;
	private String username;
	
	public NewMoviesDTO() {
		
	}
	
	public NewMoviesDTO(String name, String description, String genre, String username) {
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.username = username;
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
