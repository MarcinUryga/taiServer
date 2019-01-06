package com.marcin.demo.ingredients.controller;

import com.marcin.demo.ingredients.service.IngredientsService;
import com.marcin.demo.model.Ingredient;
import com.marcin.demo.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("recipes/{recipeId}/ingredients")
public class IngredientsController {
    @Autowired private IngredientsService ingredientsService;
    @Autowired private RecipeService recipeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Ingredient> getIngredientsByRecipeId(@PathVariable(value = "recipeId") Long recipeId) {
        return ingredientsService.findByRecipeId(recipeId).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public Ingredient createIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                       @Valid @RequestBody Ingredient ingredient) {
        return recipeService.findById(recipeId).map(recipe -> {
            ingredient.setRecipe(recipe);
            return ingredientsService.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PutMapping("/update/{ingredientId}")
    public Ingredient updateIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                       @PathVariable(value = "ingredientId") Long ingredientId,
                                       @Valid @RequestBody Ingredient ingredientRequest) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return ingredientsService.findById(ingredientId).map(ingredient -> {
            ingredient.setName(ingredientRequest.getName());
            ingredient.setQuantity(ingredientRequest.getQuantity());
            return ingredientsService.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @DeleteMapping("/delete/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                              @PathVariable(value = "ingredientId") Long ingredientId) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return ingredientsService.findById(ingredientId).map(ingredient -> {
            ingredientsService.delete(ingredient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}