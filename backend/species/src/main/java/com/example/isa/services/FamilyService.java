package com.example.isa.services;

import com.example.isa.entity.Family;
import com.example.isa.repositories.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public void save(Family family){
        familyRepository.save(family);
    }

    public Optional<Family> findById(Long id){
        return familyRepository.findById(id);
    }

    public List<Family> findAll(){
        return familyRepository.findAll();
    }

    public void delete(Family family){
        familyRepository.delete(family);
    }

    public void update(Family family) {
        save(family);
    }

}
