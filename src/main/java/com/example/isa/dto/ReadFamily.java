package com.example.isa.dto;

import com.example.isa.entity.Family;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ReadFamily {
    private Long id;
    private String name;

    public static Function<Family, ReadFamily> entityToDtoMapper() {
        return family -> ReadFamily.builder()
                .id(family.getId())
                .name(family.getName())
                .build();
    }

}
