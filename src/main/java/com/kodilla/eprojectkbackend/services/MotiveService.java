package com.kodilla.eprojectkbackend.services;

import com.kodilla.eprojectkbackend.domains.Motive;
import com.kodilla.eprojectkbackend.exceptions.MotiveNotFoundException;
import com.kodilla.eprojectkbackend.repositories.MotiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotiveService {

    @Autowired
    private MotiveRepository motiveRepository;

    public List<Motive> getAllMotive(){
        return motiveRepository.findAll();
    }

    public Motive findMotiveByID(long motiveID) throws MotiveNotFoundException{
        return motiveRepository.findById(motiveID).orElseThrow(MotiveNotFoundException::new);
    }

    public Motive createMotive(final Motive motive){
        Optional<Motive> motiveOptional = motiveRepository.findById(motive.getMotiveID());
        if (! motiveOptional.isPresent()){
            return motiveRepository.save(motive);
        }
        return motive;
    }

    public Motive updateMotive(Motive motive) throws MotiveNotFoundException{
        Optional<Motive> motiveOptional = motiveRepository.findById(motive.getMotiveID());
        if (motiveOptional.isPresent()){
            return motiveRepository.save(motive);
        }
        return motive;
    }

    public void deleteMotiveByID(long motiveID) throws MotiveNotFoundException{
        Motive deleteMotive = motiveRepository.findById(motiveID).orElseThrow(MotiveNotFoundException::new);
        motiveRepository.delete(deleteMotive);
    }

    public void deleteAllMotives(){
        motiveRepository.deleteAll();
    }

    public List<Motive> findMotiveByAuthor(String motiveAuthor) {
        return motiveRepository.findByMotiveAuthor(motiveAuthor);
    }

    public List<Motive> findMotiveByRating(String motiveRating) {
        return motiveRepository.findByMotiveRating(motiveRating);
    }

    public long countAllMotives(){
        return motiveRepository.count();
    }
}