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
@Table(name = "MOTIVE")
public class Motive {

    public Motive(long motiveID, String motiveText, String motiveAuthor, String motiveRating, LocalDate motiveCreated) {
        this.motiveID = motiveID;
        this.motiveText = motiveText;
        this.motiveAuthor = motiveAuthor;
        this.motiveRating = motiveRating;
        this.motiveCreated = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOTIVE_ID", unique = true)
    private long motiveID;

    @Setter
    @Column(name = "MOTIVE_TEXT")
    private String motiveText;

    @Setter
    @Column(name = "MOTIVE_AUTHOR")
    private String motiveAuthor;

    @Setter
    @Column(name = "MOTIVE_RATING")
    private String motiveRating;

    @Column(name = "MOTIVE_CREATED")
    private LocalDate motiveCreated;

    @Override
    public String toString() {
        return "Motive{" +
                "motiveID=" + motiveID +
                ", motiveText='" + motiveText + '\'' +
                ", motiveAuthor='" + motiveAuthor + '\'' +
                ", motiveRating='" + motiveRating + '\'' +
                ", motiveCreated=" + motiveCreated +
                '}';
    }
}