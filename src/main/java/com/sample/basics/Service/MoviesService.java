package com.sample.basics.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.basics.JPA.JpaRepo;
import com.sample.basics.Pojo.Movies;

@Service
public class MoviesService {

    @Autowired
    private JpaRepo moviesRepository;

    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    public Movies getMovieById(int id) {
        return moviesRepository.findById(id).orElse(null);
    }

    public List<Movies> getMoviesByCriteria(String searchBy,String searchValue) {
    	switch (searchBy.toLowerCase()) {
        case "movie name":
            return moviesRepository.findByMovieNameStartingWithIgnoreCase(searchValue);
        case "director": 
            return moviesRepository.findByDirectorContainingIgnoreCase(searchValue);
        case "producer":
            return moviesRepository.findByproducerContainingIgnoreCase(searchValue);
        case "hero name":
            return moviesRepository.findByCastHeroNameContainingIgnoreCase(searchValue);
        case "heroine name":
            return moviesRepository.findByCastHeroineNameContainingIgnoreCase(searchValue);
        case "villain":
            return moviesRepository.findByCastVillainContainingIgnoreCase(searchValue);
        
        default:
            throw new IllegalArgumentException("Invalid search criteria: " + searchBy);
    }
    }
    
    public List<Movies> getMoviesByRating(Double rating)
    {
    	return moviesRepository.findByRating(rating);
    }
    public List<Movies> getMoviesByFilters(List<String> genres, List<String> languages, Double rating) {
    	Integer genreCount = (genres != null && !genres.isEmpty()) ? genres.size() : null;
    	String language = (languages != null && !languages.isEmpty()) ? languages.get(0) : null;
    	
//    	if (genreCount == 0 && langCount == 0 && rating == null)
//    	    return moviesRepository.findAll();
    	 if (genreCount!=null && genreCount.intValue() > 1 && language != null && rating != null)
            return moviesRepository.findByGenresInAndLanguagesInAndRatingGreaterThan(genres, languages, genreCount.intValue(), rating);
        //else if (langCount != 0 && rating != null)
//            return moviesRepository.findByLanguagesInAndRatingGreaterThan(languages, langCount, rating);
//        else if (genreCount != 0 && rating != null)
//            return moviesRepository.findByGenresInAndRatingGreaterThan(genres, genreCount, rating);
    	else if(genreCount!=null && genreCount.intValue() > 1 && language != null)
    		return moviesRepository.findByGenresInAndLanguagesIn(genres, languages,genreCount.intValue());
//    	else if(langCount!=0)
//    		return moviesRepository.findByLanguagesIn(languages,1);
    	else if(genreCount!=null && genreCount.intValue() > 1)
    		return moviesRepository.findByGenresIn(genres,genreCount.intValue());
//    	else
//    		return getMoviesByRating(rating);
    	else
    	return moviesRepository.findMoviesWithFilters(genres,language,rating,genreCount);
    }

    public Movies addMovie(Movies movie) {
        return moviesRepository.save(movie);
    }

    public void deleteMovieById(int id) {
        moviesRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return moviesRepository.existsById(id);
    }
}
