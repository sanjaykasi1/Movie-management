package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	
	private String movieTitle;
	private String description;
	private int movieDuration;
	private int releaseDate;
	
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Actor> actorList; 
	
	@ManyToOne
	@JoinColumn
	private Genre genre;

	
	
	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Movie(String movieTitle, String description, int movieDuration, int releaseDate) {
		super();
		this.movieTitle = movieTitle;
		this.description = description;
		this.movieDuration = movieDuration;
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return " Movie [ movieId = " + movieId + ", movieTitle = " + movieTitle + ", description = " + description
				+ ", movieDuration = " + movieDuration + ", releaseDate = " + releaseDate + " ] ";
	}

	public Movie() {
		super();
	}
	
	
	

}
