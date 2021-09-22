package com.ecomindo.onboarding.sampleapi.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="movies")
public class MoviesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;
	
	public MoviesModel() {
		
	}
	
	public MoviesModel(String name, String description, String genre, String createdBy, Date createdDate, String lastUpdatedBy, Date lastUpdatedDate) {
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public long getId() {
		return id;
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
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	@Override
	public String toString() {
		return "Movies [id=" + id + ", name=" + name + ", description=" + description + ", genre=" + genre + ", createdBy= " + createdBy + ", createdDate =" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}
}
