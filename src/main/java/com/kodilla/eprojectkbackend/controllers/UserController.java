package com.kodilla.eprojectkbackend.controllers;

import com.kodilla.eprojectkbackend.domains.UserDto;
import com.kodilla.eprojectkbackend.mappers.UserMapper;
import com.kodilla.eprojectkbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


}
