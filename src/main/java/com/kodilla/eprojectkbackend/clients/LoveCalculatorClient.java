package com.kodilla.eprojectkbackend.clients;

import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class LoveCalculatorClient {
    @Autowired
    private RestTemplate restTemplate;

    public LoveCalculatorDto getPercentage(String fname, String sname){
        URI url = UriComponentsBuilder.fromHttpUrl("https://love-calculator.p.rapidapi.com/getPercentage")
                .queryParam("fname", fname)
                .queryParam("sname", sname)
                .build().encode().toUri();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "eed475f455msh189defeb54fd454p10c246jsnfc481d54b8cf");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<LoveCalculatorDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, LoveCalculatorDto.class);
        return response.getBody();

    }
}