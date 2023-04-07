package com.example.isa.repositories;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
    List<Species> findAllByFamily(Family family);
}
