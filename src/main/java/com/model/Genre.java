package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private long genreId;
	private String genreName;
	
	
	@OneToMany(mappedBy = "genre", cascade =  CascadeType.ALL)
	private List<Movie> movie;


	public long getGenreId() {
		return genreId;
	}


	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}


	public String getGenreName() {
		return genreName;
	}


	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}


	public List<Movie> getMovie() {
		return movie;
	}


	public void setMovie(List<Movie> movieList) {
		this.movie = movieList;
	}


	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}


	@Override
	public String toString() {
		return " [ genreId = " + genreId + ", genreName = " + genreName + " ] ";
	}


	public Genre() {
		super();
	}
	
	
	
	
	
}
