package com.kodilla.eprojectkbackend.clients;

import com.kodilla.eprojectkbackend.configuration.QuotesConfiguration;
import com.kodilla.eprojectkbackend.domains.QuotesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class QuotesClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QuotesConfiguration quotesConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesClient.class);

    public QuotesDto getRandomQuoteClient() {

        LOGGER.info("Starting method getRandomQuoteClient");

        URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/random")
                .build().encode().toUri();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
        headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

        LOGGER.info("Result: " + response.getBody());

        return response.getBody();
    }

    public QuotesDto getQuoteByKeywordClient(String keyword) {
        LOGGER.info("Starting method getQuoteByKeywordClient");
        LOGGER.info("Keyword " + keyword);

        URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/keyword/" + keyword)
                .build().encode().toUri();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
        headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

        LOGGER.info("Result: " + response.getBody());

        return response.getBody();
    }

    public QuotesDto getQuoteByAuthorClient(String author) {
        LOGGER.info("Starting method getQuoteByAuthorClient");
        LOGGER.info("Keyword " + author);
        URI uri = UriComponentsBuilder.fromHttpUrl("https://150000-quotes.p.rapidapi.com/author/" + author)
                .build().encode().toUri();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("x-rapidapi-host", quotesConfiguration.getQuotesEndpoint());
        headers.add("x-rapidapi-key", quotesConfiguration.getQuotesKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<QuotesDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, QuotesDto.class);

        LOGGER.info("Result: " + response.getBody());

        return response.getBody();
    }
}
