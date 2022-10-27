package com.example.isa.repositories;

import com.example.isa.entity.Species;
import com.example.isa.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpeciesRepository {
    private final Storage storage;

    public SpeciesRepository(Storage storage) {
        this.storage = storage;
    }

    public void save(Species species){
        storage.getSpecies().add(species);
    }

    public Species find(long ID){
        for(Species s : storage.getSpecies()){
            if (s.getID() == ID)
                return s;
        }
        return null;
    }

    public List<Species> findAll(){
        return storage.getSpecies();
    }

    public void delete(Species species){
        storage.getSpecies().remove(species);
    }

}
