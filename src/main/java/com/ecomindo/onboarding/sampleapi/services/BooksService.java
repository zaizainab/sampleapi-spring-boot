package com.ecomindo.onboarding.sampleapi.services;

import java.util.concurrent.Future;

import com.ecomindo.onboarding.sampleapi.model.BooksModel;

public interface BooksService {
	public BooksModel insert(String title, String description, String author);

	public Future<BooksModel> insertLongTime(String title, String description, String author);
}
