package com.marcin.demo.ingredients.service;

import com.marcin.demo.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IIngredientsService {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(long id);

    Optional<List<Ingredient>> findByRecipeId(Long recipeId);

    Ingredient save(Ingredient ingredient);

    void delete(Ingredient ingredient);
}