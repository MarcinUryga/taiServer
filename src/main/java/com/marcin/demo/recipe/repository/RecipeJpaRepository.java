package com.marcin.demo.recipe.repository;

import com.marcin.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {
//    Optional<List<Recipe>> findByUserId(Long userId);
}
