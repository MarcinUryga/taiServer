package com.marcin.demo.recipe_step.service;

import com.marcin.demo.model.RecipeStep;

import java.util.List;
import java.util.Optional;

public interface RecipeStepsService {
    List<RecipeStep> findAll();

    Optional<RecipeStep> findById(long id);

    Optional<List<RecipeStep>> findByRecipeId(Long recipeId);

    RecipeStep save(RecipeStep recipeStep);

    void delete(RecipeStep recipeStep);
}