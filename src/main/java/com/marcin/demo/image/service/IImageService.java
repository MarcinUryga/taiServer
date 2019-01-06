package com.marcin.demo.image.service;

import com.marcin.demo.model.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {
    List<Image> findAll();

    Optional<Image> findById(long id);

    Optional<List<Image>> findByRecipeId(Long recipeId);

    Optional<Image> save(Image image);

    void delete(Image image);
}

