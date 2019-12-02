package com.kodilla.eprojectkbackend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userID;
    private String userName;
    private String userLastname;

}
