package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.LoveCalculator;
import com.kodilla.eprojectkbackend.domains.LoveCalculatorDto;
import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoveCalculatorMapper {

    public LoveCalculator mapToLoveCalculator(LoveCalculatorDto loveCalculatorDto){
        return new LoveCalculator(
                loveCalculatorDto.getFname(),
                loveCalculatorDto.getSname(),
                loveCalculatorDto.getPercentage(),
                loveCalculatorDto.getResult()
        );
    }

    public LoveCalculatorDto mapToLoveCalculatorDto(LoveCalculator loveCalculator){
        return new LoveCalculatorDto(
                loveCalculator.getLoveCalculatorID(),
                loveCalculator.getFname(),
                loveCalculator.getSname(),
                loveCalculator.getPercentage(),
                loveCalculator.getResult()
        );
    }

    public List<LoveCalculatorDto> mapToMotiveDtoList(final List<LoveCalculator> loveCalculatorList){
        return loveCalculatorList.stream()
                .map(this::mapToLoveCalculatorDto)
                .collect(Collectors.toList());
    }

    public List<LoveCalculator> mapToLoveCalculatorList(final List<LoveCalculatorDto> loveCalculatorDtoList){
        return loveCalculatorDtoList.stream()
                .map(this::mapToLoveCalculator)
                .collect(Collectors.toList());
    }

}
