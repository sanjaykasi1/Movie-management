package com.services;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.*;
import com.repository.*;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class MovieService {
	
	@Autowired
	private MovieRepository mr;
	
	@Autowired
	private GenreRepository gr;
	
	@Autowired
	private ActorRepository ar;
	
	
	public void saveMovie(Movie mov) {
		mr.save(mov);
	}
	
	public void deleteMovie(long id) {
		
		//Genre  g = mr.findGenre(id);
		
		Optional<Movie> mov = mr.findById(id);
		Movie existingMovie = mov.get();
		existingMovie.setGenre(null);
		mr.delete(existingMovie);
	}
	
	
	public List<Movie> getAllMovies(){
		return mr.findAll();
	}

	public Movie getMovie(long mId) {
		// TODO Auto-generated method stub
		return mr.findById(mId).get();
	}
	
	public void updateMovie(long id, Movie m) {
		Movie existMovie = mr.findById(id).get();
		existMovie.setMovieTitle(m.getMovieTitle());
		existMovie.setDescription(m.getDescription());
		existMovie.setMovieDuration(m.getMovieDuration());
		existMovie.setReleaseDate(m.getReleaseDate());
		mr.save(existMovie);
	}
	
	public Movie getById(long id) {
		Movie existMovie = mr.findById(id).get();
		return existMovie;
	}

	public List<Movie> getMovieByGenre(long gId) {
		// TODO Auto-generated method stub
		Genre g = gr.findById(gId).get();
		
		return mr.findAllByGenre(g);
	}

	
	
}
