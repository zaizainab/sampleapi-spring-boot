package com.ecomindo.onboarding.sampleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecomindo.onboarding.sampleapi.redis.model.MoviesRedisModel;
import com.ecomindo.onboarding.sampleapi.redis.service.MoviesRedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    MoviesRedisService movieRedisService;


    @RequestMapping(value = "/get/{name}", method=RequestMethod.GET)
	public ResponseEntity<?> getHatById(@PathVariable String name) {
		try {
			List<MoviesRedisModel> movie = movieRedisService.getMovieByName(name);
            if(movie == null){
                String msg = "Data not found in redis";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
            
			return new ResponseEntity<>(movie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/add", method=RequestMethod.POST)
	public ResponseEntity<?> addHat(@RequestBody MoviesRedisModel body) {
		try {
			String msg = "";
			
            if(body.getName() == null || body.getName().isEmpty()) {
                msg = "Movie name is required";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }

            if(body.getGenre() == null || body.getGenre().isEmpty()) {
                msg = "Genre cannot be empty";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }

			movieRedisService.addMovieToRedis(body);
            msg = "Successfuly added new movie";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/update", method=RequestMethod.POST)
	public ResponseEntity<?> updateHat(@RequestBody MoviesRedisModel body) {
		try {
			String msg = "";
			List<MoviesRedisModel> movie = movieRedisService.getMovieByName(body.getName());
            
            if(movie == null){
                msg = "Movie " + body.getName() + " not found in redis";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }

            if(body.getName() == null || body.getName().isEmpty()) {
                msg = "Movie name is required";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }

            if(body.getGenre() == null || body.getGenre().isEmpty()) {
                msg = "Genre cannot be empty";
            }

            movieRedisService.updateMovieInRedis(body);
            msg = "Successfuly updated movie";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @RequestMapping(value = "/delete/{name}", method=RequestMethod.POST)
	public ResponseEntity<?> deleteHat(@PathVariable String name) {
		try {
			String msg = "";
			List<MoviesRedisModel> movie = movieRedisService.getMovieByName(name);
            
            if(movie == null){
                msg = "Movie " + name + " not found in redis";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }

            movieRedisService.deleteMovieInRedisByName(name);
            msg = "Movie " + name + "deleted";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
