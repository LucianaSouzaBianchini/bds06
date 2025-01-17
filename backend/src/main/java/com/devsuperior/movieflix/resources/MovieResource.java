package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {
	
	@Autowired
	MovieService service;
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll(
			@RequestParam(value="genreId", defaultValue = "0") Long genreId,
			Pageable pageable) {
		
		Page<MovieDTO> page = service.findAllPaged(genreId, pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
		MovieDTO entity = service.findById(id);
		return ResponseEntity.ok().body(entity);
	}
/*	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findMovieReviews(@PathVariable Long id){
//		List<ReviewDTO> resultList = service.findOneMovieReviews(id);
		List<ReviewDTO> list = service.findMovieWithReviews(id);
		return ResponseEntity.ok().body(list);
	}	
*/	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> findReviewFromMoview(@PathVariable Long id){
		List<ReviewDTO> list = service.findReviewFromMoview(id);
		return ResponseEntity.ok().body(list);
	}
	
}
