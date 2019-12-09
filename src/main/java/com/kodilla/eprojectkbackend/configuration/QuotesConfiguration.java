package com.kodilla.eprojectkbackend.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class QuotesConfiguration {
    @Value("${quotes.api.endpoint.prod}")
    private String quotesEndpoint;

    @Value("${quotes.api.key}")
    private String quotesKey;
}
