package com.kodilla.eprojectkbackend.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TVSHOWS")
public class TvShow {

    public TvShow(long tvShowID, String tvShowTitle, String tvShowCategory, String tvShowRating, LocalDate tvShowCreated) {
        this.tvShowID = tvShowID;
        this.tvShowTitle = tvShowTitle;
        this.tvShowCategory = tvShowCategory;
        this.tvShowRating = tvShowRating;
        this.tvShowCreated = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TVSHOW_ID", unique = true)
    private long tvShowID;

    @Setter
    @Column(name = "TVSHOW_TITLE")
    private String tvShowTitle;

    @Setter
    @Column(name = "TVSHOW_CATEGORY")
    private String tvShowCategory;

    @Setter
    @Column(name = "TVSHOW_RATING")
    private String tvShowRating;

    @Column(name = "TVSHOW_CREATED")
    private LocalDate tvShowCreated;

    @Override
    public String toString() {
        return "TvShow{" +
                "tvShowID=" + tvShowID +
                ", tvShowTitle='" + tvShowTitle + '\'' +
                ", tvShowCategory='" + tvShowCategory + '\'' +
                ", tvShowRating='" + tvShowRating + '\'' +
                ", tvShowCreated=" + tvShowCreated +
                '}';
    }
}