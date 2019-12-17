package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Override
    List<Movie> findAll();

    List<Movie> findByMovieDirector(String movieAuthor);

    List<Movie> findByMovieRating(String movieRating);

    @Override
    Optional<Movie> findById(Long id);

    @Override
    void deleteById(Long id);

}