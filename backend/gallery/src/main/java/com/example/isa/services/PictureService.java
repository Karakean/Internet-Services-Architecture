package com.example.isa.services;

import com.example.isa.entity.Picture;
import com.example.isa.repositories.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void create(Picture picture){
        pictureRepository.save(picture);
    }

    public Optional<Picture> findById(Long id){
        return pictureRepository.findById(id);
    }

    public List<Picture> findAll(){
        return pictureRepository.findAll();
    }

    public void delete(Picture picture){
        pictureRepository.delete(picture);
    }

    public void update(Picture picture) {
        create(picture);
    }

}
