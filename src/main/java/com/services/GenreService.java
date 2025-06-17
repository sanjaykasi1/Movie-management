package com.services;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.*;
import com.repository.*;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class GenreService {
	
	@Autowired
	private GenreRepository gr;

	public void saveGenre(Genre g) {
		gr.save(g);
	}
	
	public void deleteGenre(long id) {
		Genre g = gr.findById(id).get();
		gr.delete(g);
	}

	public List<Genre> getAllGenres() {
		return gr.findAll();
	}

	public Genre getGenre(long cId) {
		// TODO Auto-generated method stub
		return gr.findById(cId).get();
	}
	
	public void updateGenre(Long genreId, Genre g) {
		
		Optional<Genre> existingGenre = gr.findById(genreId);
		
		if(!existingGenre.isEmpty()) {
			Genre oldGenre = existingGenre.get();
			oldGenre.setGenreName(g.getGenreName());
			gr.save(oldGenre);
			
		}else {
			System.out.println(" No Genre found for this id :: "+g.getGenreId());
		}
	}
}
