package com.example.isa.controllers;

import com.example.isa.dto.DownloadAllPicturesGetResponse;
import com.example.isa.dto.DownloadPictureGetResponse;
import com.example.isa.entity.Picture;
import com.example.isa.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pictures")
public class PictureController {
    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPicture(@RequestParam("picture") MultipartFile picture,
                                                @RequestParam("author") String author,
                                                @RequestParam("description") String description) {
        String fileName = picture.getOriginalFilename();
        assert fileName != null;
        String extension = fileName.substring(fileName.lastIndexOf("."));
        Picture pictureInfo = new Picture(author, description, extension);
        pictureService.create(pictureInfo);
        Long id = pictureInfo.getId();
        String filePath = "/uploaded/" + id + extension;
        File uploadedFile = new File(filePath);
        try {
            picture.transferTo(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.accepted().body(String.valueOf(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<DownloadPictureGetResponse> downloadPicture(@PathVariable("id") Long id){
        Picture pictureInfo = pictureService.findById(id).orElseThrow();
        String extension = pictureInfo.getExtension();
        File file = new File("/uploaded/" + id + extension);
        byte[] resource = null;
        try {
            resource = Files.readAllBytes(file.toPath());
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", id + extension);

        DownloadPictureGetResponse response = new DownloadPictureGetResponse();
        response.setResource(resource);
        response.setAuthor(pictureInfo.getAuthor());
        response.setDescription(pictureInfo.getDescription());
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<DownloadAllPicturesGetResponse> downloadAllPicturesResponseEntity() {
        List<Picture> all = pictureService.findAll();
        DownloadAllPicturesGetResponse pictures = new DownloadAllPicturesGetResponse();
        for (Picture picture : all){
            File file = new File("/uploaded/" + picture.getId() + picture.getExtension());
            byte[] resource = null;
            try {
                resource = Files.readAllBytes(file.toPath());
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }

            DownloadPictureGetResponse element = new DownloadPictureGetResponse();
            element.setResource(resource);
            element.setAuthor(picture.getAuthor());
            element.setDescription(picture.getDescription());

            pictures.add(element);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "idk");

        return new ResponseEntity<>(pictures, headers, HttpStatus.OK);
    }
}
