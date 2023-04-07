package com.example.isa.dto;

import com.example.isa.entity.Species;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ReadAllSpecies {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class SpeciesEntry {
        private Long id;
        private String name;
    }

    @Singular("oneSpecies")
    List<SpeciesEntry> species;

    public static Function<Collection<Species>, ReadAllSpecies> entityToDtoMapper() {
        return species -> {
            ReadAllSpeciesBuilder builder = ReadAllSpecies.builder();
            species.stream()
                    .map(oneSpecies -> SpeciesEntry.builder()
                            .id(oneSpecies.getId())
                            .name(oneSpecies.getName())
                            .build())
                    .forEach(builder::oneSpecies);
            return builder.build();
        };
    }

}
