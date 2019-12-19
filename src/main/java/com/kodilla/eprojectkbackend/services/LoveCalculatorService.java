package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.repositories.LoveCalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoveCalculatorService {

    @Autowired
    private LoveCalculatorRepository loveCalculatorRepository;

    public LoveCalculator createLoveCalculator(final LoveCalculator loveCalculator) {
        return loveCalculatorRepository.save(loveCalculator);
    }

    public List<LoveCalculator> getAllResults() {
        return loveCalculatorRepository.findAll();
    }
}