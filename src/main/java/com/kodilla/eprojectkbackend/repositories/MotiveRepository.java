package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.Motive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MotiveRepository extends CrudRepository<Motive, Long> {

    @Override
    List<Motive> findAll();

    List<Motive> findByMotiveAuthor (String motiveAuthor);

    List<Motive> findByMotiveRating (String motiveRating);

    @Override
    Optional<Motive> findById (Long id);

    @Override
    void deleteById (Long id);
}
