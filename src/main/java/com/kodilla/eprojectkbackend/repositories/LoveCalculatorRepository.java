package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.Book;
import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LoveCalculatorRepository extends CrudRepository<LoveCalculator, Long> {

    @Override
    List<LoveCalculator> findAll();
}
