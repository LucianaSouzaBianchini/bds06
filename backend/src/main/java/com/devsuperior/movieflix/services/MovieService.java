package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	AuthService autoService;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);		
		autoService.authenticated();
		Page<Movie> list = repository.find(genre, pageable);		
		Page<MovieDTO> page = list.map(x -> new MovieDTO(x));
		return page;		
	}
	
	@Transactional(readOnly = true)
	public List<MovieDTO> findAll(){
		List<Movie> list = repository.findAll(Sort.by("title"));
		return list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		autoService.authenticated();
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
		return new MovieDTO(entity);
	}

}
