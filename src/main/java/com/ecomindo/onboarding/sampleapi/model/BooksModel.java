package com.ecomindo.onboarding.sampleapi.model;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class BooksModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "author")
	private String author;

	public BooksModel() {

	}

	public BooksModel(String title, String description, String author) {
		this.title = title;
		this.description = description;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", desc=" + description + ", author=" + author + "]";
	}
}