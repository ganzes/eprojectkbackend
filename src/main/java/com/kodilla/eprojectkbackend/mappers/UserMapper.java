package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.User;
import com.kodilla.eprojectkbackend.domains.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserName(),
                userDto.getUserLastname()
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getUserID(),
                user.getUserName(),
                user.getUserLastname()
        );
    }
}
