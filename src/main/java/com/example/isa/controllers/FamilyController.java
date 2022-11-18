package com.example.isa.controllers;

import com.example.isa.dto.CreateFamily;
import com.example.isa.dto.ReadAllFamilies;
import com.example.isa.dto.ReadFamily;
import com.example.isa.dto.UpdateFamily;
import com.example.isa.entity.Family;
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
@RequestMapping("api/families")
public class FamilyController {
    private final SpeciesService speciesService;
    private final FamilyService familyService;

    @Autowired
    public FamilyController(SpeciesService speciesService, FamilyService familyService) {
        this.speciesService = speciesService;
        this.familyService = familyService;
    }

    @GetMapping
    public ResponseEntity<ReadAllFamilies> readAllFamiliesResponseEntity() {
        List<Family> all = familyService.findAll();
        Function<Collection<Family>, ReadAllFamilies> mapper = ReadAllFamilies.entityToDtoMapper();
        ReadAllFamilies response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadFamily> readFamilyResponseEntity(@PathVariable("id") Long id){
        return familyService.findById(id)
                .map(value -> ResponseEntity.ok(ReadFamily.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createFamilyResponseEntity(@RequestBody CreateFamily request, UriComponentsBuilder builder) {
        Family family = CreateFamily.dtoToEntityMapper().apply(request);
        familyService.save(family);
        return ResponseEntity.created(builder.pathSegment("api", "families", "{id}").buildAndExpand(family.getId()).toUri()).build();
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFamilyResponseEntity(@PathVariable("id") Long id) {
        Optional<Family> familyOptional = familyService.findById(id);
        if (familyOptional.isPresent()) {
            speciesService.deleteByFamily(familyOptional.get());
            familyService.delete(familyOptional.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFamilyResponseEntity(@RequestBody UpdateFamily request, @PathVariable("id") Long id) {
        Optional<Family> familyOptional = familyService.findById(id);
        if (familyOptional.isPresent()) {
            Family updatedFamily = UpdateFamily.dtoToEntityUpdater().apply(familyOptional.get(), request);
            familyService.update(updatedFamily);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
