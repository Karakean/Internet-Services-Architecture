package com.example.isa.services;

import com.example.isa.entity.Family;
import com.example.isa.event.repository.FamilyEventRepository;
import com.example.isa.repositories.FamilyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyEventRepository familyEventRepository;

    public FamilyService(FamilyRepository familyRepository, FamilyEventRepository familyEventRepository) {
        this.familyRepository = familyRepository;
        this.familyEventRepository = familyEventRepository;
    }

    @Transactional
    public void create(Family family){
        familyRepository.save(family);
        familyEventRepository.create(family);
    }

    public Optional<Family> findById(Long id){
        return familyRepository.findById(id);
    }

    public List<Family> findAll(){
        return familyRepository.findAll();
    }

    @Transactional
    public void delete(Family family){
        familyEventRepository.delete(family);
        familyRepository.delete(family);
    }

    public void update(Family family) {
        create(family);
    }

}
