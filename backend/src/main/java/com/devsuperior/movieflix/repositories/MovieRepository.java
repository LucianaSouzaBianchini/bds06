package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query("SELECT obj FROM Movie obj WHERE "
			+ "(:genre IS NULL OR :genre = obj.genre) "
			+ "ORDER BY obj.title")
	Page<Movie> find(Genre genre, Pageable pageable);
/*
	@Query("SELECT obj FROM Review obj WHERE "
			+ "obj.movie.id = :movieId")
	List<Review> findMovieWithReviews(Long movieId);
*/	
	/*
	@Query("from Review obj where obj.movie.id = :movieId")
	List<Review> findMovieReviews(Long movieId);
	
	@Query("from Movie obj where obj.genre.id = :genreId order by obj.title")
	Page<Movie> pagedMoviesByGenreId(Long genreId, Pageable pageable);	
*/
}
