package com.sample.basics.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.basics.Pojo.Movies;

@Repository
public interface JpaRepo extends JpaRepository<Movies,Integer> {

	List<Movies> findByMovieNameStartingWithIgnoreCase(String movieName);
	
	
	List<Movies> findByDirectorContainingIgnoreCase(String director);

	List<Movies> findByproducerContainingIgnoreCase(String producer);
	
	List<Movies> findByCastHeroNameContainingIgnoreCase(String heroName);
	List<Movies> findByCastHeroineNameContainingIgnoreCase(String heroineName);
	List<Movies> findByCastVillainContainingIgnoreCase(String villain);

	@Query(value = "SELECT m.* " +
            "FROM movies m " +
            "JOIN movies_genres mg ON m.id = mg.movies_id " +
            "JOIN movies_languages ml1 ON m.id = ml1.movies_id " +
            "WHERE mg.genres IN :genres " +
            "AND ml1.languages IN :languages " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT mg.genres) = :genreCount", nativeQuery = true)
	List<Movies> findByGenresInAndLanguagesIn(@Param("genres") List<String> genres,
                                        @Param("languages") List<String> languages,
                                        @Param("genreCount") int genreCount);

//	@Query(value = "SELECT m.* " +
//            "FROM movies m " +
//            "JOIN movies_languages ml1 ON m.id = ml1.movies_id " +
//            "WHERE ml1.languages IN :languages " +
//            "GROUP BY m.id " +
//            "HAVING COUNT(DISTINCT ml1.languages) = :langCount", nativeQuery = true)
//	List<Movies> findByLanguagesIn(@Param("languages") List<String> languages,
//            						@Param("langCount") int langCount);
//	
	@Query(value = "SELECT m.* " +
            "FROM movies m " +
            "JOIN movies_genres mg1 ON m.id = mg1.movies_id " +
            "WHERE mg1.genres IN :genres " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT mg1.genres) = :genreCount", nativeQuery = true)
	List<Movies> findByGenresIn(@Param("genres") List<String> genres, @Param("genreCount") int genreCount);
	
	@Query(value = "SELECT m.*" +
					"FROM movies m " +
					"WHERE m.rating >= :rating", nativeQuery = true)
	List<Movies> findByRating(@Param("rating") Double rating);
//	
	@Query(value = "SELECT m.* " +
            "FROM movies m " +
            "JOIN movies_genres mg ON m.id = mg.movies_id " +
            "JOIN movies_languages ml ON m.id = ml.movies_id " +
            "WHERE mg.genres IN :genres " +
            "AND ml.languages IN :languages " +
            "AND m.rating > :rating " +
            "GROUP BY m.id " +
            "HAVING COUNT(DISTINCT mg.genres) = :genreCount", nativeQuery = true)
	List<Movies> findByGenresInAndLanguagesInAndRatingGreaterThan(
	        @Param("genres") List<String> genres,
	        @Param("languages") List<String> languages,
	        @Param("genreCount") int genreCount,
	        @Param("rating") Double rating);
//	
//	@Query(value = "SELECT m.* " +
//            "FROM movies m " +
//            "JOIN movies_genres mg ON m.id = mg.movies_id " +
//            "WHERE mg.genres IN :genres " +
//            "AND m.rating > :rating " +
//            "GROUP BY m.id " +
//            "HAVING COUNT(DISTINCT mg.genres) = :genreCount", nativeQuery = true)
//	List<Movies> findByGenresInAndRatingGreaterThan(
//	        @Param("genres") List<String> genres,
//	        @Param("genreCount") int genreCount,
//	        @Param("rating") Double rating);
//	
//	@Query(value = "SELECT m.* " +
//            "FROM movies m " +
//            "JOIN movies_languages ml ON m.id = ml.movies_id " +
//            "WHERE ml.languages IN :languages " +
//            "AND m.rating > :rating " +
//            "GROUP BY m.id " +
//            "HAVING COUNT(DISTINCT ml.languages) = :langCount", nativeQuery = true)
//	List<Movies> findByLanguagesInAndRatingGreaterThan(
//	        @Param("languages") List<String> languages,
//	        @Param("langCount") int langCount,
//	        @Param("rating") Double rating);

	@Query(value = "SELECT m.* " +
	        "FROM movies m " +
	        "LEFT JOIN movies_genres mg ON m.id = mg.movies_id " +
	        "LEFT JOIN movies_languages ml ON m.id = ml.movies_id " +
	        "WHERE (:genres IS NULL OR (mg.genres IS NOT NULL AND mg.genres IN (:genres))) " +
	        "AND (:languages IS NULL OR (ml.languages IS NOT NULL AND ml.languages = :languages)) " +
	        "AND (:rating IS NULL OR m.rating >= :rating) " +
	        "GROUP BY m.id " +
	        "HAVING (:genreCount IS NULL OR COUNT(DISTINCT mg.genres) = :genreCount) ", nativeQuery = true)
	List<Movies> findMoviesWithFilters(
	        @Param("genres") List<String> genres,
	        @Param("languages") String languages,
	        @Param("rating") Double rating,
	        @Param("genreCount") Integer genreCount);


}

