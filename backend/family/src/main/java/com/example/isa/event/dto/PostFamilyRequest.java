package com.example.isa.event.dto;

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
public class PostFamilyRequest {
    private Long id;

    public static Function<Family, PostFamilyRequest> entityToDtoMapper() {
        return fam -> PostFamilyRequest.builder()
                .id(fam.getId())
                .build();
    }
}
