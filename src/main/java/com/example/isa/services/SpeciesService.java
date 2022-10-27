package com.example.isa.services;

import com.example.isa.entity.Species;
import com.example.isa.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public void save(Species Species){
        speciesRepository.save(Species);
    }

    public Species find(long ID){
        return speciesRepository.find(ID);
    }

    public List<Species> findAll(){
        return speciesRepository.findAll();
    }

    public void delete(Species species){
        speciesRepository.delete(species);
    }
}
