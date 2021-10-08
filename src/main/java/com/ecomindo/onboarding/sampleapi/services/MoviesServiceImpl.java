package com.ecomindo.onboarding.sampleapi.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomindo.onboarding.sampleapi.dao.MoviesDao;
import com.ecomindo.onboarding.sampleapi.dto.NewMoviesDTO;
import com.ecomindo.onboarding.sampleapi.dto.UpdateMovieDTO;
import com.ecomindo.onboarding.sampleapi.model.MoviesModel;

@Service
public class MoviesServiceImpl implements MoviesService {
	
	@Autowired
	MoviesDao moviesDao;
	
    private ExecutorService executor = Executors.newFixedThreadPool(2);

	@Transactional
	@Override
	public MoviesModel insert(String name, String description, String genre, String username) {
		try {
			Date moment = Date.from(Instant.now());	
			
			MoviesModel movie = moviesDao.save(new MoviesModel(name, description, genre, username, moment, username, moment));
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional
	@Override
	public MoviesModel insert2(MoviesModel moviesModel) {
		try {
			
			MoviesModel movie = moviesDao.save(moviesModel);
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MoviesModel update(UpdateMovieDTO movieDto) {
		try {
			
			MoviesModel movie = moviesDao.findById(movieDto.getId()).orElseThrow();
			
			if(movie.getId() > 0) {
				movie.setName(movieDto.getName());
				movie.setDescription(movieDto.getDescription());
				movie.setGenre(movieDto.getGenre());
				movie.setLastUpdatedBy(movieDto.getLastUpdatedBy());
				
				moviesDao.save(movie);
			}
			
			return movie;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean delete(long id) {

		if(!moviesDao.existsById(id))
			return false;
		
		moviesDao.deleteById(id);
		return true;
	}

	@Override
	public List<MoviesModel> getMovies() {

		return StreamSupport.stream(moviesDao.findAll().spliterator(), false)
//				.map(e-> Mapper.convertBookToBookDto(e, true, true, true))
				.collect(Collectors.toList());
	}

	@Override
	public List<MoviesModel> getMoviesByName(String name) {
		return StreamSupport.stream(moviesDao.findByName(name).spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Future<Void> upload(List<String> content) {
		return executor.submit(() -> {
            try{
                String denominator = "	";
                for(String items : content){
                    String name = items.split(denominator)[0]; //.replaceAll("\uFEFF", "");
                    String description = items.split(denominator)[1];
                    String genre = items.split(denominator)[2];
    
                    List<MoviesModel> movie = moviesDao.findByName(name);
                    if(movie.size()>0){
                        moviesDao.delete(movie.get(0));
                    }
                    
                    MoviesModel newMovie = new MoviesModel();
                    newMovie.setName(name);
                    newMovie.setDescription(description);
                    newMovie.setGenre(genre);
                    newMovie.setCreatedDate(Date.from(Instant.now()));
                    newMovie.setCreatedBy("upload user");
                    
                    moviesDao.save(newMovie);
                }
            }catch(Exception e){
                throw e;
            }     
            
            return null;
        });
	}
	
}
