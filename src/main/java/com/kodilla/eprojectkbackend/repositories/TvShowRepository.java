package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.TvShow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TvShowRepository extends CrudRepository<TvShow, Long> {

    @Override
    List<TvShow> findAll();

    List<TvShow> findByTvShowCategory(String tvShowCategory);

    List<TvShow> findByTvShowRating(String tvShowRating);

    @Override
    Optional<TvShow> findById(Long id);

    @Override
    void deleteById(Long id);

}
