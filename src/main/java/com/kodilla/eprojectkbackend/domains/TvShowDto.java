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
public class TvShowDto {

    private long tvShowID;
    private String tvShowTitle;
    private String tvShowCategory;
    private String tvShowRating;
    private LocalDate tvShowCreated;

    @Override
    public String toString() {
        return "TvShowDto{" +
                "tvShowID=" + tvShowID +
                ", tvShowTitle='" + tvShowTitle + '\'' +
                ", tvShowCategory='" + tvShowCategory + '\'' +
                ", tvShowRating='" + tvShowRating + '\'' +
                ", tvShowCreated=" + tvShowCreated +
                '}';
    }
}