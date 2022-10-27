package com.example.isa;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Storage {
    List<Family> families= new ArrayList<>();
    List<Species> species = new ArrayList<>();

    public List<Family> getFamilies() {
        return families;
    }

    public List<Species> getSpecies() {
        return species;
    }
}
