package com.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Actor;
import com.model.Genre;
import com.repository.ActorRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ActorService {
	
	@Autowired
	private ActorRepository ar;
	
	public void saveActor(Actor a) {
		ar.save(a);
	}
	
	public void deleteActor(long id) {
		Actor a = ar.findById(id).get();
		a.setMovie(null);
		ar.delete(a);
	}
	
	public List<Actor> getAllActor(){
		return ar.findAll();
	}
	
	public void updateActor(long id, Actor a) {
		 Actor existingActor = ar.findById(id).get();
		 existingActor.setActorName(a.getActorName());
		 existingActor.setRole(a.getRole());
		 ar.save(existingActor);
	}
	
	public Actor getActor(long id) {
		return ar.findById(id).get();
	}
	
	

}
