package com.marcin.demo.ingredients.service;

import com.marcin.demo.ingredients.repository.IngredientsJpaRepository;
import com.marcin.demo.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService implements IIngredientsService {
    @Autowired private IngredientsJpaRepository ingredientsJpaRepository;

    @Override
    public List<Ingredient> findAll() {
        return ingredientsJpaRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(long id) {
        return ingredientsJpaRepository.findById(id);
    }

    @Override
    public Optional<List<Ingredient>> findByRecipeId(Long recipeId) {
        return ingredientsJpaRepository.findByRecipeId(recipeId);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        ingredientsJpaRepository.save(ingredient);
        return ingredientsJpaRepository.findById(ingredient.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientsJpaRepository.delete(ingredient);
    }
}