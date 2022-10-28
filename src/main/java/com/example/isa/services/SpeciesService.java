package com.example.isa.services;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import com.example.isa.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public void save(Species Species){
        speciesRepository.save(Species);
    }

    public Optional<Species> findById(Long ID){
        return speciesRepository.findById(ID);
    }

    public List<Species> findAll(){
        return speciesRepository.findAll();
    }

    public void delete(Species species){
        speciesRepository.delete(species);
    }

    public void deleteByFamily(Family family) {
        for(Species s : family.getSpecies()){
            delete(s);
        }
    }
    public void update(Species updatedSpecies) {
        save(updatedSpecies);
    }
}
