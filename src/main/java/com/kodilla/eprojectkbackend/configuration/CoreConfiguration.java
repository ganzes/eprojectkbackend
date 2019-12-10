package com.kodilla.eprojectkbackend.configuration;

import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@Configuration
public class CoreConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private MotiveRepository motiveRepository;

/*    @Scheduled(fixedRate = 5000)
    public void check(){
        long howManyItemsAreInDatabase = motiveRepository.count();
        System.out.println("TTTTTEEEEEESSSSSTTTTTT " + howManyItemsAreInDatabase);
    }*/
}
