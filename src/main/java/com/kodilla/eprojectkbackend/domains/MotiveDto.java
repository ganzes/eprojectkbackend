package com.kodilla.eprojectkbackend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MotiveDto {
    private Long motiveID;
    private String motiveText;
    private String motiveAuthor;
    private int motiveRating;
}