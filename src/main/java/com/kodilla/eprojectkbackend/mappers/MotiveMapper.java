package com.kodilla.eprojectkbackend.mappers;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import org.springframework.stereotype.Component;

@Component
public class MotiveMapper {

    public Motive mapToMotive(final MotiveDto motiveDto){
        return new Motive(
                motiveDto.getMotiveText(),
                motiveDto.getMotiveAuthor(),
                motiveDto.getMotiveRating()
        );
    }

    public MotiveDto mapToMotiveDto (final Motive motive){
        return new MotiveDto(
                motive.getMotiveID(),
                motive.getMotiveText(),
                motive.getMotiveAuthor(),
                motive.getMotiveRating()
        );
    }
}
