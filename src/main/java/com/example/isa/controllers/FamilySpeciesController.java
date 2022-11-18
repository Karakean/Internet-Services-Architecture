package com.example.isa.controllers;

import com.example.isa.dto.*;
import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import com.example.isa.services.FamilyService;
import com.example.isa.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Optional;


@RestController
@RequestMapping("/api/families/{family}/species")
public class FamilySpeciesController {
    private final SpeciesService speciesService;
    private final FamilyService familyService;

    @Autowired
    public FamilySpeciesController(SpeciesService speciesService, FamilyService familyService) {
        this.speciesService = speciesService;
        this.familyService = familyService;
    }

    @GetMapping
    public ResponseEntity<ReadAllSpecies> readAllSpeciesResponseEntity(@PathVariable("family") Long id) {
        return familyService.findById(id)
                .map(value -> ResponseEntity.ok(ReadAllSpecies.entityToDtoMapper().apply(speciesService.findAllByFamily(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadSpecies> readSpeciesResponseEntity(@PathVariable("id") Long speciesId){
        return speciesService.findById(speciesId)
                .map(value -> ResponseEntity.ok(ReadSpecies.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSpeciesResponseEntity(@PathVariable("family") Long id,
                                                            @RequestBody CreateSpecies request, UriComponentsBuilder builder) {
        Optional<Family> familyPresence = familyService.findById(id);
        if (familyPresence.isPresent()) {
            Species species = CreateSpecies
                    .dtoToEntityMapper(x -> familyService.findById(Long.valueOf(x)).orElseThrow())
                    .apply(request);
            speciesService.save(species);
            return ResponseEntity.created(builder.pathSegment("api", "families", "{family}", "species", "{id}")
                    .buildAndExpand(species.getId()).toUri()).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSpeciesResponseEntity(@PathVariable("id") Long id) {
        Optional<Species> speciesOptional = speciesService.findById(id);
        if (speciesOptional.isPresent()) {
            speciesService.delete(speciesOptional.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSpeciesResponseEntity(@RequestBody UpdateSpecies request, @PathVariable("id") Long id) {
        Optional<Species> speciesOptional = speciesService.findById(id);
        if (speciesOptional.isPresent()) {
            Species updatedSpecies = UpdateSpecies.dtoToEntityUpdater().apply(speciesOptional.get(), request);
            speciesService.update(updatedSpecies);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
