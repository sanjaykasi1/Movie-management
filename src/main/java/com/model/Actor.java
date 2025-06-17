package com.model;

import jakarta.persistence.*;

@Entity
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long actorId;
	
	private String actorName;
	private String role;
	
	@ManyToOne
	@JoinColumn
	private Movie movie;

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor(String actorName, String role) {
		super();
		this.actorName = actorName;
		this.role = role;
	}

	@Override
	public String toString() {
		return "[ actorId = " + actorId + ", actorName = " + actorName + ", role = " + role + " ] \n";
	}

	public Actor() {
		super();
	}
	
	
	
}
