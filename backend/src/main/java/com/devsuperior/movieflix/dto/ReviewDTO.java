package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message="Campo requerido")
	@NotBlank(message="Campo requerido")
	private String text;
	
	private UserDTO user;
	private MovieDTO movie;

	private Long movieId;
	
	public ReviewDTO() {
	}
	
	public ReviewDTO(Long id, String text, UserDTO user, MovieDTO movie) {		
		this.id = id;
		this.text = text;
		this.user = user;
		this.movie = movie;		
	}
	
	public ReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movie = new MovieDTO(entity.getMovie().getId(), null, null, null, "", "", null);
		user = new UserDTO(entity.getUser());
		movieId = movie.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

}
