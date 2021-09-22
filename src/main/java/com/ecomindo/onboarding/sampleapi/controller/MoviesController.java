package com.ecomindo.onboarding.sampleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.dao.MoviesDao;
import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.dto.ResponseDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;
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
	@GetMapping(value = "movies/{name}")
	public ResponseEntity<List<MoviesModel>> getMoviesByName(@ApiParam(value = "Name of movie", required = true) @PathVariable String name){
		return ResponseEntity.ok().body(moviesService.getMoviesByName(name));
	}
	
	@ApiOperation("Get movies by name")
	@PostMapping(value = "/name", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MoviesModel>> getMoviesByName2(@RequestBody String name){
		return ResponseEntity.ok().body(moviesService.getMoviesByName(name));
	}
}
