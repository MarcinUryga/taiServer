package com.marcin.demo.image.controller;

import com.marcin.demo.image.service.ImageService;
import com.marcin.demo.ingredients.service.IngredientsService;
import com.marcin.demo.model.Image;
import com.marcin.demo.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("recipes/{recipeId}/images")
public class ImageController {
    @Autowired private ImageService imageService;

    @Autowired private IngredientsService ingredientsService;
    @Autowired private RecipeService recipeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Image> getImageByRecipeId(@PathVariable(value = "recipeId") Long recipeId) {
        return imageService.findByRecipeId(recipeId).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public Optional<Image> createImage(@PathVariable(value = "recipeId") Long recipeId,
                                       @Valid @RequestBody Image image) {
        return recipeService.findById(recipeId).map(recipe -> {
            image.setRecipe(recipe);
            return imageService.save(image);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PutMapping("/update/{imageId}")
    public Optional<Image> updateIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                            @PathVariable(value = "imageId") Long imageId,
                                            @Valid @RequestBody Image imageRequest) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return imageService.findById(imageId).map(image -> {
            image.setImagesUrl(imageRequest.getImagesUrl());
            return imageService.save(image);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "recipeId") Long recipeId,
                                         @PathVariable(value = "imageId") Long imageId) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return imageService.findById(imageId).map(image -> {
            imageService.delete(image);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
