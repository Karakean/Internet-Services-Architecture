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
public class CreateSpecies {
    private String name;
    private boolean hallucinogenic;
    private Long familyId;

    public static Function<CreateSpecies, Species> dtoToEntityMapper(Function<Long, Family> familyFunction) {
            return speciesDto -> Species.builder()
                    .name(speciesDto.getName())
                    .hallucinogenic(speciesDto.isHallucinogenic())
                    .family(familyFunction.apply(speciesDto.familyId))
                    .build();
        }
}
