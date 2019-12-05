package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.User;
import com.kodilla.eprojectkbackend.domains.UserDto;
import com.kodilla.eprojectkbackend.exceptions.UserNotFoundException;
import com.kodilla.eprojectkbackend.mappers.UserMapper;
import com.kodilla.eprojectkbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("eprojectk/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userMapper.mapToUser(userDto));
    }

    @GetMapping(value = "/getUser")
    public UserDto getUser(@RequestParam Long userID) throws UserNotFoundException{
        return userMapper.mapToUserDto(userService.getUserByID(userID));
    }

    @GetMapping(value = "/getUsers")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @PutMapping(value = "/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException{
        User userUpdate = userService.updateUser(userMapper.mapToUser(userDto));
        return userMapper.mapToUserDto(userUpdate);
    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(@RequestParam Long userID) throws UserNotFoundException {
        userService.deleteUser(userID);
    }
}
