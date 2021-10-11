package com.ecomindo.onboarding.sampleapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.dao.MoviesDao;
import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;
import com.ecomindo.onboarding.sampleapi.services.FileService;
import com.ecomindo.onboarding.sampleapi.services.MoviesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	MoviesDao moviesDao;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	MoviesService moviesService;
	
	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO insert(@RequestBody NewMoviesDTO newMoviesDTO) {
		ResponseDTO response = new ResponseDTO();
		try {
			MoviesModel movie = moviesService.insert(newMoviesDTO.getName(), newMoviesDTO.getDescription(), newMoviesDTO.getGenre(), newMoviesDTO.getUsername());
			
			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(movie);
			
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}
	
//	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<MoviesModel> update(@RequestBody UpdateMovieDTO updateMoviesDTO) {
//		return ResponseEntity.ok().body(moviesService.update(updateMoviesDTO));
//	}
	
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO update(@RequestBody UpdateMovieDTO updateMoviesDTO) {
		ResponseDTO response = new ResponseDTO();
		try {
			MoviesModel movie = moviesService.update(new UpdateMovieDTO(updateMoviesDTO.getId(), 
															updateMoviesDTO.getName(), 
															updateMoviesDTO.getDescription(), 
															updateMoviesDTO.getGenre(), 
															updateMoviesDTO.getLastUpdatedBy()));
			
			response.setCode("200");
			response.setMessage("Insert Success");
			response.setData(movie);
			
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Insert Failed");
			return response;
		}
	}
	
	@ApiOperation("Delete a movie")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Boolean> delete(@ApiParam(value = "Id of movie", required = true) @PathVariable long id){
		try {
			return ResponseEntity.ok().body(moviesService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(false);
		}
		
	}
	
	@ApiOperation("Delete a movie")
	@DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete2(@RequestBody long id){
		return ResponseEntity.ok().body(moviesService.delete(id));
	}
	
	@ApiOperation("Get all movies")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MoviesModel>> getAll(){
		return ResponseEntity.ok().body(moviesService.getMovies());
	}
	
	@ApiOperation("Get movies by name")
	@RolesAllowed(value = "User")
	@GetMapping(value = "movies/{name}")
	public ResponseEntity<List<MoviesModel>> getMoviesByName(@ApiParam(value = "Name of movie", required = true) @PathVariable String name){
		return ResponseEntity.ok().body(moviesService.getMoviesByName(name));
	}
	
	@ApiOperation("Get movies by name")
	@PostMapping(value = "/name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MoviesModel>> getMoviesByName2(@RequestBody String name){
		return ResponseEntity.ok().body(moviesService.getMoviesByName(name));
	}
	
	@ApiOperation("upload file from SFTP")
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public ResponseDTO upload(@RequestBody String filename) {
		ResponseDTO response = new ResponseDTO();
		try {
			List<String> fileContent = fileService.getFileContentByFilename(filename);
			
			if(fileContent.isEmpty())
				throw new Exception("There is no such file");
			
			fileContent.remove(0);
			
			List<String> firstBatchValue = new ArrayList<>(fileContent.subList(0, (fileContent.size())/2));
			List<String> secondBatchValue = new ArrayList<>(fileContent.subList((fileContent.size())/2, fileContent.size()));
			Future<Void> firstBatch = moviesService.upload(firstBatchValue);
			Future<Void> secondBatch = moviesService.upload(secondBatchValue);
			
			
			while (!(firstBatch.isDone() && secondBatch.isDone())) {
			  if(!firstBatch.isDone()){
			  System.out.println("Add file From File Job first batch is running..."); }
			  
			  if(!secondBatch.isDone()){
				  System.out.println("Add file From File Job second batch is running..."); 
			  }
			  
//			  if(firstBatch.isDone() && secondBatch.isDone()) {
//				  System.out.println("Processing all batch is done..."); 
//			  }
//			  if(firstBatch.isCancelled() || secondBatch.isCancelled())
//				  System.out.println("Error...");
			  
			  Thread.sleep(500); 
			}
			 
			
			response.setCode("200");
			response.setMessage("Uploaded the files successfully");

			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage("Could not upload files: " + e.getMessage());
			return response;
		}
	}
	
	@RequestMapping(value = "/check-user", method=RequestMethod.GET)
	@RolesAllowed(value = {"user"})
	public ResponseEntity<?> checkAuthUser(Authentication authentication) {
		try {
			return new ResponseEntity<>(authentication, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/check-admin", method=RequestMethod.GET)
	@RolesAllowed(value = {"Admin"})
	public ResponseEntity<?> checkAuthAdmin(Authentication authentication) {
		try {
			return new ResponseEntity<>(authentication, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
