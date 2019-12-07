package com.kodilla.eprojectkbackend.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class LoveCalculatorConfiguration {

    @Value("${lovecalculator.api.endpoint.prod}")
    private String loveCalculatorEndpoint;

    @Value("${lovecalculator.api.key}")
    private String loveCalculatorKey;

}
