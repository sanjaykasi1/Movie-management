package com.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.repository.*;
import com.services.*;
import com.model.*;

@Controller
public class MovieController {
	@Autowired
	ActorService as;
	@Autowired
	GenreService gs;
	@Autowired
	MovieService ms;
		
	@GetMapping("/")
	public ResponseEntity<String> dummyHome(){
		return new ResponseEntity<String>("hello guys put /home please" , HttpStatus.OK);
	}
	
	@GetMapping("/home")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/test")
	public String testing() {
		return "test";
	}
	
	@GetMapping("/movie")
	public String movie(Model model) {
		List<Genre> genre = gs.getAllGenres();
		model.addAttribute("genres", genre);
		model.addAttribute("movie", new Movie());
		
		return "movie";
	}
	
	
	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie,  @RequestParam long cId) {
		Genre genre = gs.getGenre(cId);
		movie.setGenre(genre);
		ms.saveMovie(movie);
		return "redirect:/home";
		
	}
	
	@GetMapping("/genre")
	public String showGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "add-genre";
	}
	
	@PostMapping("/saveGenre")
	public String saveGenre(@ModelAttribute("genre") Genre genre) {
		gs.saveGenre(genre);
		return "redirect:/home";
	}
	
	
	@GetMapping("/actor")
	public String addActor(Model model) {
		List<Movie> movie = ms.getAllMovies();
		model.addAttribute("movies", movie);
		model.addAttribute("actor", new Actor());
		return "actor";
	}
	
	
	
	@PostMapping("/saveActor")
	public String saveActor(@ModelAttribute("actor") Actor actor, @RequestParam long mId) {
		Movie movie = ms.getMovie(mId);
		actor.setMovie(movie);
		as.saveActor(actor);
		return "redirect:/home";
		
	}
	
	
	@GetMapping("/view")
	public String viewAll(Model model) {
		List<Movie> mov = ms.getAllMovies();
		List<Genre> gen = gs.getAllGenres();
		List<Actor> act = as.getAllActor();
		
		model.addAttribute("movies", mov);
		model.addAttribute("genres", gen);
		model.addAttribute("actors", act);
		
		return "view";
		
	}
	
	
	@GetMapping("/updateGenre/{id}")
	public String showUpdateGenrePage(@PathVariable(value="id") long id, Model model) {
		Genre g = gs.getGenre(id);
		model.addAttribute("genre", g);
		return "update-genre";
	}
	
	@PostMapping("/updateGenre")
	public String updateGenre(@ModelAttribute("genre") Genre genre, @RequestParam long genreId) {
		gs.updateGenre(genreId,genre);
		
		return "redirect:/view";
	}
	
	
	@GetMapping("/updateMovie/{id}")
	public String showUpdateMoviePage(@PathVariable(value="id") long id, Model model) {
		Movie m = ms.getMovie(id);
		List<Genre> genres = gs.getAllGenres();
		model.addAttribute("genre", genres);
		model.addAttribute("movie", m);
		return "update-movie";
	}
	
	@PostMapping("/updateMovie")
	public String updateMovie(@ModelAttribute("movie") Movie movie, @RequestParam long movieId, @RequestParam long genreId) {
		Genre g = gs.getGenre(genreId);
		movie.setGenre(g);
		ms.saveMovie(movie);
		return "redirect:/view";
	}
	
	@GetMapping("/updateActor/{id}")
	public String showUpdateActorPage(@PathVariable(value="id") long id, Model model) {
		Actor a = as.getActor(id);
		List<Movie> moviesList = ms.getAllMovies();
		model.addAttribute("movies", moviesList);
		model.addAttribute("actor", a);
		return "update-actor";
	}
	
	@PostMapping("/updateActor")
	public String updateActor(@ModelAttribute("actor") Actor actor, @RequestParam long actorId, @RequestParam long movieId) {
		Movie m = ms.getMovie(movieId);
		actor.setMovie(m);
		as.updateActor(actorId,actor);
		return "redirect:/view";
	}
	
	
	@RequestMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable (name="id") long id, Model model) {
		
		ms.deleteMovie(id);
	   	return "redirect:/view";
	}
	
	@RequestMapping("/deleteGenre/{id}")
	public String deleteGenre(@PathVariable (name="id") long id, Model model) {
		
		gs.deleteGenre(id);
	   	return "redirect:/view";
	}
	
	
	@RequestMapping("/deleteActor/{id}")
	public String deleteActor(@PathVariable (name="id") long id, Model model) {
		
		as.deleteActor(id);
	   	return "redirect:/view";
	}
	
	
	
	
	@GetMapping("/viewById")
	public String viewBy(Model model) {
		return "viewById";
	}
	
	
	@GetMapping("/viewByMovie")
	public String viewByMovie( @RequestParam long id, Model model) {
		Movie mov = ms.getById(id);
		List<Actor> actorlist = mov.getActorList();
		Genre g = mov.getGenre();
		model.addAttribute("movies", mov);
		model.addAttribute("actors", actorlist);
		model.addAttribute("genre", g);
		return "viewByMovie";
	}
	
	@GetMapping("/viewByCategory")
	public String viewByCat(Model model) {
		List<Genre> gList = gs.getAllGenres();
		model.addAttribute("genres", gList);
		return "viewByCategory";
	}
	
	@PostMapping("/viewCategoryMovie")
	public String viewCatMovie(@RequestParam(name = "genreId") long gId, Model model)
	{
		List<Movie> movList = ms.getMovieByGenre(gId);
		model.addAttribute("movies", movList);
		return "viewCategoryMovie";
	}
	

	
	
}
