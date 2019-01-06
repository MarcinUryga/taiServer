package com.marcin.demo.ingredients.repository;

import com.marcin.demo.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IngredientsJpaRepository extends JpaRepository<Ingredient, Long> {

    Optional<List<Ingredient>> findByRecipeId(Long recipeId);
}