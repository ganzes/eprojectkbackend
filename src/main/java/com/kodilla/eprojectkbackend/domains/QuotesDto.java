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
public class QuotesDto {
    @JsonProperty("message")
    private String message;
    @JsonProperty("author")
    private String author;
    @JsonProperty("keywords")
    private String keywords;
    @JsonProperty("profession")
    private String profession;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("authorBirth")
    private String authorBirth;
    @JsonProperty("authorDeath")
    private String authorDeath;

    @Override
    public String toString() {
        return "QuotesDto{" +
                "message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", keywords='" + keywords + '\'' +
                ", profession='" + profession + '\'' +
                ", nationality='" + nationality + '\'' +
                ", authorBirth='" + authorBirth + '\'' +
                ", authorDeath='" + authorDeath + '\'' +
                '}';
    }
}
