package com.kodilla.eprojectkbackend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotiveDto {
    private Long motiveID;
    private String motiveText;
    private String motiveAuthor;
    private String motiveRating;
}