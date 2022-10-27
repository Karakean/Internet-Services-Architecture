package com.example.isa.repositories;

import com.example.isa.entity.Family;
import com.example.isa.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyRepository {
    private final Storage storage;


    public FamilyRepository(Storage storage) {
        this.storage = storage;
    }

    public void save(Family family){
        storage.getFamilies().add(family);
    }

    public Family find(long ID){
        for(Family f : storage.getFamilies()){
            if (f.getID() == ID)
                return f;
        }
        return null;
    }

    public List<Family> findAll(){
        return storage.getFamilies();
    }

    public void delete(Family Family){
        storage.getFamilies().remove(Family);
    }
}
