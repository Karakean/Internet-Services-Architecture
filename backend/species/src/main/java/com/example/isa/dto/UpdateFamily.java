package com.example.isa.dto;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import lombok.*;

import java.util.List;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFamily {
    private String name;
    private int classificationYear;

    public static BiFunction<Family, UpdateFamily, Family> dtoToEntityUpdater() {
        return (family, familyDto) -> {
            family.setName(familyDto.getName());
            family.setClassificationYear(familyDto.getClassificationYear());
            return family;
        };
    }

}
