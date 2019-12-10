package com.kodilla.eprojectkbackend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotiveDto {
    private long motiveID;
    private String motiveText;
    private String motiveAuthor;
    private String motiveRating;
    private LocalDate motiveCreated;
}