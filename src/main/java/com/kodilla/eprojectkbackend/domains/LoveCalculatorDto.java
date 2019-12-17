package com.kodilla.eprojectkbackend.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoveCalculatorDto {

    /*@JsonProperty("loveCalculatorID")
    private long loveCalculatorID;*/

    @JsonProperty("fname")
    private String fname;

    @JsonProperty("sname")
    private String sname;

    @JsonProperty("percentage")
    private String percentage;

    @JsonProperty("result")
    private String result;

    @Override
    public String toString() {
        return "LoveCalculatorDto{" +
                "fname='" + fname + '\'' +
                ", sname='" + sname + '\'' +
                ", percentage='" + percentage + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}