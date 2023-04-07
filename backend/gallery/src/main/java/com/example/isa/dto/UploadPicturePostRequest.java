package com.example.isa.dto;

import com.example.isa.entity.Picture;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UploadPicturePostRequest {
    private String author;
    private String description;
    public static Function<UploadPicturePostRequest, Picture> dtoToEntityMapper() {
        return pictureDto -> Picture.builder()
                .author(pictureDto.getAuthor())
                .description(pictureDto.getDescription())
                .build();
    }
}
