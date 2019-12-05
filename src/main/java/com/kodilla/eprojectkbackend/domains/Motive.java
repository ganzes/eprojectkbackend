package com.kodilla.eprojectkbackend.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MOTIVE")
public class Motive {

    public Motive(String motiveText, String motiveAuthor, String motiveRating) {
        this.motiveText = motiveText;
        this.motiveAuthor = motiveAuthor;
        this.motiveRating = motiveRating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOTIVE_ID", unique = true)
    private Long motiveID;

    @Setter
    @Column(name = "MOTIVE_TEXT")
    private String motiveText;

    @Setter
    @Column(name = "MOTIVE_AUTHOR")
    private String motiveAuthor;

    @Setter
    @Column(name = "MOTIVE_RATING")
    private String motiveRating;

}