package com.kodilla.eprojectkbackend.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "USERS")
public class User {

    public User(String userName, String userLastname) {
        this.userName = userName;
        this.userLastname = userLastname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private Long userID;

    @Getter
    @Setter
    @Column(name = "USER_NAME")
    private String userName;

    @Getter
    @Setter
    @Column(name = "USER_LASTNAME")
    private String userLastname;
}
