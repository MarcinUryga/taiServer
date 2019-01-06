package com.marcin.demo.image.service;

import com.marcin.demo.image.repository.ImageJpaRepository;
import com.marcin.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {
    @Autowired private ImageJpaRepository imageJpaRepository;

    @Override
    public List<Image> findAll() {
        return imageJpaRepository.findAll();
    }

    @Override
    public Optional<Image> findById(long id) {
        return imageJpaRepository.findById(id);
    }

    @Override
    public Optional<List<Image>> findByRecipeId(Long recipeId) {
        return imageJpaRepository.findByRecipeId(recipeId);
    }

    @Override
    public Optional<Image> save(Image image) {
        imageJpaRepository.save(image);
        return imageJpaRepository.findById(image.getId());
    }

    @Override
    public void delete(Image image) {
        imageJpaRepository.delete(image);
    }

}
