package com.marcin.demo.recipe_step.controller;

import com.marcin.demo.model.RecipeStep;
import com.marcin.demo.recipe.service.RecipeService;
import com.marcin.demo.recipe_step.service.RecipeStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("recipes/{recipeId}/steps")
public class RecipeStepsController {
    @Autowired private RecipeStepsService recipeStepsService;
    @Autowired private RecipeService recipeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<RecipeStep> getRecipeStepsByRecipeId(@PathVariable(value = "recipeId") Long recipeId) {
        return recipeStepsService.findByRecipeId(recipeId).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public RecipeStep createRecipeStep(@PathVariable(value = "recipeId") Long recipeId,
                                       @Valid @RequestBody RecipeStep recipeStep) {
        return recipeService.findById(recipeId).map(recipe -> {
            recipeStep.setRecipe(recipe);
            return recipeStepsService.save(recipeStep);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PutMapping("/update/{recipeStepId}")
    public RecipeStep updateIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                       @PathVariable(value = "recipeStepId") Long recipeStepId,
                                       @Valid @RequestBody RecipeStep recipeStepRequest) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return recipeStepsService.findById(recipeStepId).map(recipeStep -> {
            recipeStep.setName(recipeStepRequest.getName());
            recipeStep.setStepNumber(recipeStepRequest.getStepNumber());
            return recipeStepsService.save(recipeStep);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @DeleteMapping("/delete/{recipeStepId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                              @PathVariable(value = "recipeStepId") Long recipeStepId) {
        if (!recipeService.existsById(recipeId)) {
            throw new ResourceNotFoundException("not found");
        }

        return recipeStepsService.findById(recipeStepId).map(recipeStep -> {
            recipeStepsService.delete(recipeStep);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}