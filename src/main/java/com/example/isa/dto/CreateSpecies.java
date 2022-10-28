package com.example.isa.dto;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateSpecies {
    private Long id;
    private String name;
    private boolean hallucinogenic;
    private Family family;

    public static Function<CreateSpecies, Species> dtoToEntityMapper(
        Function<String, Family> familyFunction,
        Supplier<Species> speciesSupplier) {
            return speciesDto -> Species.builder()
                    .id(speciesDto.getId())
                    .name(speciesDto.getName())
                    .hallucinogenic(speciesDto.isHallucinogenic())
                    .family(speciesDto.getFamily())
                    .build();
        }
}
