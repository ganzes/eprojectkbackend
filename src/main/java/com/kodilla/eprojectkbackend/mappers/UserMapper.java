package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.User;
import com.kodilla.eprojectkbackend.domains.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserName(),
                userDto.getUserLastname()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserID(),
                user.getUserName(),
                user.getUserLastname()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }
}
