package com.example.isa;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import com.example.isa.services.SpeciesService;
import com.example.isa.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    FamilyService familyService;
    SpeciesService speciesService;

    @Autowired
    public DataInitializer(FamilyService familyService, SpeciesService speciesService) {
        this.familyService = familyService;
        this.speciesService = speciesService;
    }

    @PostConstruct
    public void init(){
        familyService.save(new Family(1L, "Agaricaceae", 1826));
        familyService.save(new Family(2L, "Amanitaceae", 1940));
        familyService.save(new Family(3L, "Boletaceae", 1826));
        familyService.save(new Family(4L, "Cantharellaceae", 1888));
        familyService.save(new Family(5L, "Russulaceae", 1907));
        speciesService.save(new Species(1L, "Amanita phalloides", familyService.find(2L), true));
        speciesService.save(new Species(2L, "Boletus edulis", familyService.find(3L), false));
    }
}
