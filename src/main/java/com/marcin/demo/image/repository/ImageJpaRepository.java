package com.marcin.demo.image.repository;

import com.marcin.demo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ImageJpaRepository extends JpaRepository<Image, Long> {
    Optional<List<Image>> findByRecipeId(Long recipeId);
}
