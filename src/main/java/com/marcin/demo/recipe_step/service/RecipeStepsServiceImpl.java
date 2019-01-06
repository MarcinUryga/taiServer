package com.marcin.demo.recipe_step.service;

import com.marcin.demo.model.RecipeStep;
import com.marcin.demo.recipe_step.repository.RecipeStepsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeStepsServiceImpl implements RecipeStepsService {
    @Autowired private RecipeStepsJpaRepository recipeStepsJpaRepository;

    @Override
    public List<RecipeStep> findAll() {
        return recipeStepsJpaRepository.findAll();
    }

    @Override
    public Optional<RecipeStep> findById(long id) {
        return recipeStepsJpaRepository.findById(id);
    }

    @Override
    public Optional<List<RecipeStep>> findByRecipeId(Long recipeId) {
        return recipeStepsJpaRepository.findByRecipeId(recipeId);
    }

    @Override
    public RecipeStep save(RecipeStep recipeStep) {
        recipeStepsJpaRepository.save(recipeStep);
        return recipeStepsJpaRepository.findById(recipeStep.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public void delete(RecipeStep recipeStep) {
        recipeStepsJpaRepository.delete(recipeStep);
    }
}