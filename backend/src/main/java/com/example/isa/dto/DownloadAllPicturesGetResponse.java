package com.example.isa.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class DownloadAllPicturesGetResponse {
    List<DownloadPictureGetResponse> pictures;

    public DownloadAllPicturesGetResponse() {
        pictures = new ArrayList<>();
    }

    public void add(DownloadPictureGetResponse picture){
        pictures.add(picture);
    }
}
