package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    List<Book> findByBookAuthor (String bookAuthor);

    List<Book> findByBookRating (String bookRating);

    @Override
    Optional<Book> findById (Long id);

    @Override
    void deleteById (Long id);

}
