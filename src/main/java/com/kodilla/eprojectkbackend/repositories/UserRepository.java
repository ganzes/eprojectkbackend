package com.kodilla.eprojectkbackend.repositories;

import com.kodilla.eprojectkbackend.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository <User, Long> {

    @Override
    List<User> findAll();
}
