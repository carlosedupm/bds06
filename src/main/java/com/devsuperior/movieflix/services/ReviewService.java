package com.devsuperior.movieflix.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		try {
			Review entity = new Review();
			User user = authService.authenticated();
			entity.setMovie(movieRepository.findById(dto.getMovieId()).get());
			entity.setText(dto.getText());
			entity.setUser(user);
			entity = repository.save(entity);
			return new ReviewDTO(entity, user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id movie not found " + dto.getMovieId());
		}
	}

}
