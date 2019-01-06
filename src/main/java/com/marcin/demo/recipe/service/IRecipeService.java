package com.marcin.demo.recipe.service;

import com.marcin.demo.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface IRecipeService {
    List<Recipe> findAll();

    Optional<Recipe> findById(long id);

    Recipe save(Recipe recipe);

//    Optional<List<Recipe>> findByUserId(Long userId);

    boolean existsById(Long recipeId);

    void delete(Recipe recipe);
}
