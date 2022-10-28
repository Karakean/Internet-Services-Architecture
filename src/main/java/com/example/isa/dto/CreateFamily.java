package com.example.isa.dto;

import com.example.isa.entity.Family;
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
public class CreateFamily {
    private Long id;
    private String name;
    private int classificationYear;

    public static Function<CreateFamily, Family> dtoToEntityMapper() {
        return familyDto -> Family.builder()
                .id(familyDto.getId())
                .name(familyDto.getName())
                .classificationYear(familyDto.getClassificationYear())
                .build();
    }
}
