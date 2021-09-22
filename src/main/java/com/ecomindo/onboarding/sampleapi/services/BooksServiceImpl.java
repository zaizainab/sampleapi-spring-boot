package com.ecomindo.onboarding.sampleapi.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dao.BooksDao;
import com.ecomindo.onboarding.sampleapi.model.BooksModel;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	BooksDao booksDao;

	private ExecutorService executor = Executors.newFixedThreadPool(2);

	@Transactional
	@Override
	public BooksModel insert(String title, String description, String author) {
		try {
			BooksModel books = booksDao.save(new BooksModel(title, description, author));
			return books;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Future<BooksModel> insertLongTime(String title, String description, String author) {
		return executor.submit(() -> {
			Thread.sleep(10000);
			return insert(title, description, author);
		});
	}
}
