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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final FamilyService familyService;

    @Autowired
    public SpeciesController(SpeciesService speciesService, FamilyService familyService) {
        this.speciesService = speciesService;
        this.familyService = familyService;
    }

    @GetMapping
    public ResponseEntity<ReadAllSpecies> readAllSpeciesResponseEntity() {
        List<Species> all = speciesService.findAll();
        ReadAllSpecies response = ReadAllSpecies.entityToDtoMapper().apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadSpecies> readSpeciesResponseEntity(@PathVariable("id") Long id){
        return speciesService.findById(id)
                .map(value -> ResponseEntity.ok(ReadSpecies.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSpeciesResponseEntity(@RequestBody CreateSpecies request, UriComponentsBuilder builder) {
        Optional<Species> speciesPresence = speciesService.findById(request.getId());
        if (speciesPresence.isEmpty()) {
            Species species = CreateSpecies
                    .dtoToEntityMapper(id -> familyService.findById(Long.valueOf(id)).orElseThrow(), () -> null)
                    .apply(request);
            speciesService.save(species);
            return ResponseEntity.created(builder.pathSegment("api", "species", "{id}")
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
