package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MotiveMapper {

    public Motive mapToMotive(final MotiveDto motiveDto){
        return new Motive(
                motiveDto.getMotiveID(),
                motiveDto.getMotiveText(),
                motiveDto.getMotiveAuthor(),
                motiveDto.getMotiveRating(),
                motiveDto.getMotiveCreated()
        );
    }

    public MotiveDto mapToMotiveDto (final Motive motive){
        return new MotiveDto(
                motive.getMotiveID(),
                motive.getMotiveText(),
                motive.getMotiveAuthor(),
                motive.getMotiveRating(),
                motive.getMotiveCreated()
        );
    }

    public List<MotiveDto> mapToMotiveDtoList(final List<Motive> motiveList){
        return motiveList.stream()
                .map(this::mapToMotiveDto)
                .collect(Collectors.toList());
    }

    public List<Motive> mapToMotiveList(final List<MotiveDto> motiveDtoList){
        return motiveDtoList.stream()
                .map(this::mapToMotive)
                .collect(Collectors.toList());
    }
}