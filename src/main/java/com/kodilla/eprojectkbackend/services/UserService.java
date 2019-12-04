package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.User;
import com.kodilla.eprojectkbackend.exceptions.UserNotFoundException;
import com.kodilla.eprojectkbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(final User user){
        return userRepository.save(user);
    }

    public User getUserByID(final Long userID) throws UserNotFoundException{
        return userRepository.findById(userID).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Long userID) throws UserNotFoundException{
        User deleteUser = userRepository.findById(userID).orElseThrow(UserNotFoundException::new);
        userRepository.save(deleteUser);
    }

    public User updateUser(final User user) throws UserNotFoundException{
        User updateUser = userRepository.findById(user.getUserID()).orElseThrow(UserNotFoundException::new);
        updateUser.setUserName(user.getUserName());
        updateUser.setUserLastname(user.getUserName());

        return userRepository.save(updateUser);
    }
}
