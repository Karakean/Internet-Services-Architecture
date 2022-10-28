package com.example.isa.dto;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import lombok.*;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ReadSpecies {
    private Long id;
    private String name;

    public static Function<Species, ReadSpecies> entityToDtoMapper() {
        return species -> ReadSpecies.builder()
                .id(species.getId())
                .name(species.getName())
                .build();
    }
}
