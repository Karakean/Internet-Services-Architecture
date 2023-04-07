package com.example.isa.dto;

import lombok.*;
import org.springframework.core.io.Resource;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class DownloadPictureGetResponse {
    private byte[] resource;
    private String author;
    private String description;
}
