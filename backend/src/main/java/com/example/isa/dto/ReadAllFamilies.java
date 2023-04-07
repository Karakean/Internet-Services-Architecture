//package com.example.isa.dto;
//
//import com.example.isa.entity.Family;
//import lombok.*;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.function.Function;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@ToString
//@EqualsAndHashCode
//public class ReadAllFamilies {
//    @Getter
//    @Setter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    @ToString
//    @EqualsAndHashCode
//    public static class FamilyEntry {
//        private Long id;
//        private String name;
//    }
//
//    @Singular()
//    List<ReadAllFamilies.FamilyEntry> families;
//
//    public static Function<Collection<Family>, ReadAllFamilies> entityToDtoMapper() {
//        return families -> {
//            ReadAllFamiliesBuilder builder = ReadAllFamilies.builder();
//            families.stream()
//                    .map(family -> ReadAllFamilies.FamilyEntry.builder()
//                            .id(family.getId())
//                            .name(family.getName())
//                            .build())
//                    .forEach(builder::family);
//            return builder.build();
//        };
//    }
//}
