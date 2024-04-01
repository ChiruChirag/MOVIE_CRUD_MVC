package com.chirag.mvn_assinment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chirag.mvn_assinment.model.Movie;
import com.chirag.mvn_assinment.model.MovieDto;
import com.chirag.mvn_assinment.repositories.MovieRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Movies")
public class MovieController {
	
	
	
	
	
	
	@Autowired
	private MovieRepository repo;
	
	
	
	
	
	
	@GetMapping({"","/"})
	
	public String showMovieList(Model model) {
		List<Movie>movies=repo.findAll();
		model.addAttribute("movies",movies);
		return "Movies/index";
	}
	
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		MovieDto MovieDto= new MovieDto();
		model.addAttribute("MovieDto",MovieDto);
		return "Movies/CreateMovie";
	}
	
	
	@PostMapping("/create")
	public String createMovie(@Valid @ModelAttribute MovieDto MovieDto,BindingResult result) {
		
		
		Movie movie =new Movie();
		movie.setTitle(MovieDto.getTitle());
		movie.setAverageRating(MovieDto.getAverageRating());
	    movie.setDirector(MovieDto.getDirector());
	    movie.setGenre(MovieDto.getGenre());
	    movie.setReleaseYear(MovieDto.getReleaseYear());
	  
	  repo.save(movie);
		
		
		return "redirect:/Movies";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Model model,@RequestParam int id) 
	{
		try 
		{
			Movie movie = repo.findById((long) id).orElse(null);;
			model.addAttribute("Movies",movie);
			MovieDto MovieDto= new MovieDto();
			MovieDto.setId(movie.getId());
			MovieDto.setTitle(movie.getTitle());
			MovieDto.setAverageRating(movie.getAverageRating());
			MovieDto.setDirector(movie.getDirector());
			MovieDto.setGenre(movie.getGenre());
		    model.addAttribute("MovieDto",MovieDto);
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception: "+ex.getMessage());
			return "redirect:/Movies";
			
	    }
		return "Movies/EditMovie";
	
	}
	
	@PostMapping("/edit")
	public String updateProduct(Model model,@RequestParam int id,@Valid @ModelAttribute MovieDto MovieDto,BindingResult result)
	{
		
		try {
			Movie movie=repo.findById((long) id).get();
			model.addAttribute("Movies",movie);
		if(result.hasErrors()) {
			return "Movies/EditMovie";
			
		}
		
		
		movie.setTitle(MovieDto.getTitle());
		movie.setAverageRating(MovieDto.getAverageRating());
	    movie.setDirector(MovieDto.getDirector());
	    movie.setGenre(MovieDto.getGenre());
	    movie.setReleaseYear(MovieDto.getReleaseYear());
	  
	  repo.save(movie);
		
		}
		catch(Exception ex){System.out.println("Exception:"+ex.getMessage());}
		return "redirect:/Movies";

		
	}
	
	@GetMapping("/delete")
	public String deleteMovie(@RequestParam int id) {
		try {
			Movie movie =repo.findById((long) id).get();
			
			repo.delete(movie);
			
		}catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
		}
		return "redirect:/Movies";
		
	}
	@GetMapping("/sortByIdAsc")
	public String sortCinemasByIdAsc(Model model) {
	    List<Movie> cinemas = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	    model.addAttribute("cinemas", cinemas);
	    return "Movies/index";
	}
	@GetMapping("/sortByIdDesc")
	public String sortCinemasByIdDesc(Model model) {
	    List<Movie> cinemas = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	    model.addAttribute("cinemas", cinemas);
	    return "Moviees/index";
	}

	
}

