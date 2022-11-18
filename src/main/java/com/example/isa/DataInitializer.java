package com.example.isa;

import com.example.isa.entity.Family;
import com.example.isa.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    FamilyService familyService;

    @Autowired
    public DataInitializer(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostConstruct
    public void init(){
        familyService.create(new Family(1L, "Agaricaceae", 1826));
        familyService.create(new Family(2L, "Amanitaceae", 1940));
        familyService.create(new Family(3L, "Boletaceae", 1826));
        familyService.create(new Family(4L, "Cantharellaceae", 1888));
        familyService.create(new Family(5L, "Russulaceae", 1907));
    }
}
