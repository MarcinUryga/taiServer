package com.marcin.demo.recipe.service;

import com.marcin.demo.model.Recipe;
import com.marcin.demo.recipe.repository.RecipeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService implements IRecipeService {
    @Autowired private RecipeJpaRepository recipeJpaRepository;

    @Override
    public List<Recipe> findAll() {
        return recipeJpaRepository.findAll();
    }

    @Override
    public Optional<Recipe> findById(long id) {
        return recipeJpaRepository.findById(id);
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipeJpaRepository.save(recipe);
        return recipeJpaRepository.findById(recipe.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

/*
    @Override
    public Optional<List<Recipe>> findByUserId(Long userId) {
        return recipeJpaRepository.findByUserId(userId);
    }
*/

    @Override
    public boolean existsById(Long recipeId) {
        return recipeJpaRepository.existsById(recipeId);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeJpaRepository.delete(recipe);
    }

}
