package com.ecomindo.onboarding.sampleapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.dao.BooksDao;
import com.ecomindo.onboarding.sampleapi.dto.OptionsDTO;
import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.model.BooksModel;
import com.ecomindo.onboarding.sampleapi.services.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	BooksDao booksDao;

	@Autowired
	BooksService booksService;

	@GetMapping("/get-books")
	public ResponseEntity<List<BooksModel>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			List<BooksModel> books = new ArrayList<BooksModel>();

			if (title == null)
				booksDao.findAll().forEach(books::add);
			else
				booksDao.findByTitle(title).forEach(books::add);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-books2")
	public ResponseDTO getAllBooks2(@RequestParam(required = false) String title) {
		ResponseDTO response = new ResponseDTO();
		try {

			List<BooksModel> books = new ArrayList<BooksModel>();

			if (title == null)
				booksDao.findAll().forEach(books::add);
			else
				booksDao.findByTitle(title).forEach(books::add);

			response.setCode("200");
			if (books.isEmpty()) {
				response.setMessage(HttpStatus.NO_CONTENT.toString());
			}

			response.setData(books);
			return response;
		} catch (Exception e) {
			response.setCode("500");
			return response;
		}
	}

	@GetMapping("/get-ddl-books")
	public ResponseDTO getDdlBooks() {
		ResponseDTO response = new ResponseDTO();
		try {

			List<OptionsDTO> opt = booksDao.findDDLTitle();
			response.setCode("200");
			if (opt.isEmpty()) {
				response.setMessage(HttpStatus.NO_CONTENT.toString());
			}

			response.setData(opt);
			return response;
		} catch (Exception e) {
			response.setCode("500");
			return response;
		}
	}

	@PostMapping("/insert")
	public ResponseDTO insert(@RequestBody BooksModel books) {
		ResponseDTO response = new ResponseDTO();
		try {
			BooksModel book = booksService.insert(books.getTitle(), books.getDescription(), books.getAuthor());

			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(book);

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}

	@PostMapping("/insert-future")
	public ResponseDTO insertUsingFuture(@RequestBody BooksModel books) {
		ResponseDTO response = new ResponseDTO();
		try {
			Future<BooksModel> future = booksService.insertLongTime(books.getTitle(), books.getDescription(),
					books.getAuthor());

			while (!future.isDone()) {
				System.out.println("Waiting for database process...");
				Thread.sleep(300);
			}

			BooksModel book = future.get();

			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(book);

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}

	@PostMapping("/insert-future2")
	public ResponseDTO insertUsingFuture2(@RequestBody BooksModel books) {
		ResponseDTO response = new ResponseDTO();
		try {
			Future<BooksModel> future1 = booksService.insertLongTime(books.getTitle() + "1", books.getDescription(), books.getAuthor());
			Future<BooksModel> future2 = booksService.insertLongTime(books.getTitle() + "2", books.getDescription(), books.getAuthor());
			Future<BooksModel> future3 = booksService.insertLongTime(books.getTitle() + "3", books.getDescription(), books.getAuthor());
			
			while (!(future1.isDone() && future2.isDone() && future3.isDone())) {
			    if(future1.isDone()) {
			    	System.out.println("future1 is done");
			    } else {
			    	System.out.println("future1 is not done");
			    }
			    if(future2.isDone()) {
			    	System.out.println("future2 is done");
			    }
			    else {
			    	System.out.println("future2 is not done");
			    }
			    if(future3.isDone()) {
			    	System.out.println("future3 is done");
			    }
			    else {
			    	System.out.println("future3 is not done");
			    }
			    
//				System.out.println(
//			      String.format(
//			        "future1 is %s and future2 is %s and future3 is %s", 
//			        future1.isDone() ? "done" : "not done", 
//			        future2.isDone() ? "done" : "not done",
//			        future3.isDone() ? "done" : "not done"
//			      )
//			    );
			    Thread.sleep(300);
			}

			ArrayList<BooksModel> res = new ArrayList();
			res.add(future1.get());
			res.add(future2.get());
			res.add(future3.get());
			
			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(res);
			
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}
}
