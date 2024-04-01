package com.chirag.mvn_assinment.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;



@Entity

public class MovieDto {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	
	@NotEmpty(message = "The title is required")
    private String title;
	private Long id;

	@NotEmpty(message = "The genre is required")
	private String genre;
	   
    @NotEmpty(message = "The director is required")
    private String director;
	    
	@Min(0)
	private double averageRating;
	   
	@Min(1888)
	private int releaseYear;
	   


    
	
	public MovieDto() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}	
	
	
	

}
