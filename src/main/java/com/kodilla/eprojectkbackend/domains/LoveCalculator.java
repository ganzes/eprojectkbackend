package com.kodilla.eprojectkbackend.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LOVECALCULATOR")
public class LoveCalculator {

    public LoveCalculator(String fname, String sname, String percentage, String result) {
        this.fname = fname;
        this.sname = sname;
        this.percentage = percentage;
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOVECALCULATOR_ID", unique = true)
    private long loveCalculatorID;

    @Setter
    @Column(name = "FNAME")
    private String fname;
    @Setter
    @Column(name = "SNAME")
    private String sname;
    @Setter
    @Column(name = "PERCENTAGE")
    private String percentage;
    @Setter
    @Column(name = "RESULT")
    private String result;
}
