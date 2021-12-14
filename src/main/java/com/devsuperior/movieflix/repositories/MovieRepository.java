package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	@Query("SELECT obj FROM Movie obj WHERE "
			+ "(:genreId is null OR obj.genre.id = :genreId) "
			+ "ORDER BY obj.title")
	public Page<Movie> findByGenreId(Pageable pageable,Long genreId);

}
