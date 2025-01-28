package com.sample.basics.Pojo;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Movies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int Id;

    @NotEmpty(message = "Movie name cannot be empty")
    private String movieName;

    @Positive(message = "Budget must be a positive number")
    private int budget;

    @Positive(message = "Collections must be a positive number")
    private int collections;

    @NotEmpty(message = "Producer name cannot be empty")
    private String producer;

    @NotEmpty(message = "Director name cannot be empty")
    private String director;

    @NotNull(message = "Release date cannot be null")
    private Date releaseDate;

    @NotNull(message = "Cast details cannot be null")	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cast_id")
	private Cast cast;
    
    @ElementCollection
    @NotEmpty(message = "Genres cannot be empty")
    private List<@NotEmpty(message = "Genre cannot be empty") String> genres;

    @ElementCollection
    @NotEmpty(message = "Languages cannot be empty")
    private List<@NotEmpty(message = "Language cannot be empty") String> languages;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private Double rating;

	
	public int getId() {
		return Id;
	}
	public Cast getCast() {
		return cast;
	}
	public void setCast(Cast cast) {
		this.cast = cast;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getCollections() {
		return collections;
	}
	public void setCollections(int collections) {
		this.collections = collections;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}	
	
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	

}
