package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends CrudRepository<Game, Long> {

    @Override
    List<Game> findAll();

    List<Game> findByGameDeveloper(String gameDeveloper);

    List<Game> findByGameRating(String gameRating);

    @Override
    Optional<Game> findById(Long id);

    @Override
    void deleteById(Long id);

}