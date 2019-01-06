package com.marcin.demo.recipe_step.repository;

import com.marcin.demo.model.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RecipeStepsJpaRepository extends JpaRepository<RecipeStep, Long> {

    Optional<List<RecipeStep>> findByRecipeId(Long recipeId);
}