package com.example.isa.services;

import com.example.isa.entity.Family;
import com.example.isa.repositories.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private final FamilyRepository FamilyRepository;

    public FamilyService(FamilyRepository FamilyRepository) {
        this.FamilyRepository = FamilyRepository;
    }

    public void save(Family Family){
        FamilyRepository.save(Family);
    }

    public Family find(long ID){
        return FamilyRepository.find(ID);
    }

    public List<Family> findAll(){
        return FamilyRepository.findAll();
    }

    public void delete(Family Family){
        FamilyRepository.delete(Family);
    }
}
