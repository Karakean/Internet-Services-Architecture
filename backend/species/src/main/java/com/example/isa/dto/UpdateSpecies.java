package com.example.isa.dto;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateSpecies {
    private String name;
    private boolean hallucinogenic;

    public static BiFunction<Species, UpdateSpecies, Species> dtoToEntityUpdater() {
        return (species, speciesDto) -> {
            species.setName(speciesDto.getName());
            species.setHallucinogenic(speciesDto.isHallucinogenic());
            return species;
        };
    }
}
