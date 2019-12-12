package com.kodilla.eprojectkbackend.facade;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.domains.MotiveDto;
import com.kodilla.eprojectkbackend.mappers.MotiveMapper;
import com.kodilla.eprojectkbackend.services.MotiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MotivesFacade {

    @Autowired
    private MotiveService motiveService;
    @Autowired
    private MotiveMapper motiveMapper;

    public List<Motive> getAllMotivesFacade(){
        List<MotiveDto> motiveDtoList = motiveMapper.mapToMotiveDtoList(motiveService.getAllMotive());
        return motiveMapper.mapToMotiveList(motiveDtoList);
    }

}