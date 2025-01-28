package com.sample.basics.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sample.basics.Pojo.Movies;
import com.sample.basics.Service.MoviesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies() {
        logger.info("Fetching all movies");
        List<Movies> movies = moviesService.getAllMovies();
        if (movies.isEmpty()) {
            logger.warn("No movies found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id) {
        try {
            Movies movie = moviesService.getMovieById(id);
            logger.info("Movie fetched with id: " + id);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            logger.warn("No movie found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getMovieDetailsByName(@RequestParam String searchBy, @RequestParam String searchValue) {
        try {
            List<Movies> movies = moviesService.getMoviesByCriteria(searchBy,searchValue);
            logger.info("Movies fetched with "+searchBy.toLowerCase()+ " with " + searchValue);
            return ResponseEntity.ok(movies);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid movie name input: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.warn("No movies found starting with name: " + searchValue);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movies found");
        }
    }
    @GetMapping("/byRating")
    public ResponseEntity<?> getMoviesByRating(@RequestParam(required = false) Double rating)
    {
    	try {
    		List<Movies> movies = moviesService.getMoviesByRating(rating);
    		if(movies.isEmpty())
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no movie found");
    		return ResponseEntity.ok(movies);
    	}
    	catch(Exception e)
    	{
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error fetching movies");
    	}
    }
    @GetMapping("/filters")
    public ResponseEntity<?> getMoviesByFilters(
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) List<String> languages,
            @RequestParam(required = false) Double rating) {
        try {
            List<Movies> movies = moviesService.getMoviesByFilters(genres, languages,rating);
            if (movies.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movies found");
            }
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching movies");
        }
    }
  
    @PostMapping
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movies movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            logger.error("Validation errors: " + errors);
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            Movies savedMovie = moviesService.addMovie(movie);
            logger.info("New movie added");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
        } catch (Exception e) {
            logger.error("Failed to add movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add movie");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable int id) {
        if (!moviesService.existsById(id)) {
            logger.warn("Cannot delete. Movie not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        try {
            moviesService.deleteMovieById(id);
            logger.info("Movie deleted with id: " + id);
            return ResponseEntity.ok("Movie deleted successfully");
        } catch (Exception e) {
            logger.error("Failed to delete movie: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete movie");
        }
    }
}
