package com.kodilla.eprojectkbackend.domains;

import com.kodilla.eprojectkbackend.exceptions.UserNotFoundException;
import com.kodilla.eprojectkbackend.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userSaveTest() {
        //Given
        User userSaveTest = new User("testUserName", "testUserLastname");

        //When
        userRepository.save(userSaveTest);
        long userTestID = userSaveTest.getUserID();

        //Then
        Assert.assertNotEquals(0, userTestID);

        //CleanUp
        userRepository.deleteById(userTestID);
    }

    @Test
    public void userReadTest() throws UserNotFoundException {
        //Given
        User userReadTest = new User("testUserName", "testUserLastname");

        //When
        userRepository.save(userReadTest);
        long userTestID = userReadTest.getUserID();

        //Then
        User resultReadTest = userRepository.findById(userTestID).orElseThrow(UserNotFoundException::new);

        Assert.assertEquals("testUserName", resultReadTest.getUserName());
        Assert.assertEquals("testUserLastname", resultReadTest.getUserLastname());

        //CleanUp
        userRepository.deleteById(userTestID);
    }

    @Test
    public void userUpdateTest() throws UserNotFoundException {
        //Given
        User userUpdateTest = new User("testUserName", "testUserLastname");

        //When
        userRepository.save(userUpdateTest);
        long userTestID = userUpdateTest.getUserID();

        //Then
        User updateTest = userRepository.findById(userTestID).orElseThrow(UserNotFoundException::new);
        updateTest.setUserName("testUserNameUpdated");
        userRepository.save(updateTest);

        User resultUpdateTest = userRepository.findById(userTestID).orElseThrow(UserNotFoundException::new);

        //Then
        Assert.assertEquals("testUserNameUpdated", resultUpdateTest.getUserName());

        //CleanUp
        userRepository.deleteById(userTestID);
    }

    @Test
    public void userDeleteTest() throws UserNotFoundException{
        //Given
        User userDeleteTest = new User("testUserName", "testUserLastname");

        //When
        userRepository.save(userDeleteTest);
        long userTestID = userDeleteTest.getUserID();
        long countBeforeDelete = userRepository.count();

        //Then
        userRepository.deleteById(userTestID);
        long countAfterDelete = userRepository.count();

        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }
}

