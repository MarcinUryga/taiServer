package com.marcin.demo.recipe.controller;

import com.marcin.demo.image.service.ImageService;
import com.marcin.demo.ingredients.service.IngredientsService;
import com.marcin.demo.model.Recipe;
import com.marcin.demo.recipe.service.RecipeService;
import com.marcin.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired private RecipeService recipeService;
    @Autowired private UserService userService;
    @Autowired private IngredientsService ingredientsService;
    @Autowired private ImageService imageService;

    //    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<Recipe> getRecipes() {
        return recipeService.findAll();
    }

    @RequestMapping(value = "{recipeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public Recipe getRecipesById(@PathVariable(value = "recipeId") Long recipeId) {
        return recipeService.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @PostMapping("/add")
    public Recipe createRecipe(@PathVariable(value = "userId") Long userId,
                               @Valid @RequestBody Recipe recipe) {
        return userService.findById(userId).map(user -> {
//            recipe.setUser(user);
            recipeService.save(recipe);
            if (recipe.getIngredients() != null) {
                saveIngredients(recipe);
            }
            if (recipe.getImages() != null) {
                saveImages(recipe);
            }
            return recipeService.findById(recipe.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    private void saveIngredients(@RequestBody @Valid Recipe recipe) {
        recipe.getIngredients().forEach(ingredient -> {
            ingredient.setRecipe(recipe);
            ingredientsService.save(ingredient);
        });
    }

    private void saveImages(@RequestBody @Valid Recipe recipe) {
        recipe.getImages().forEach(image -> {
            image.setRecipe(recipe);
            imageService.save(image);
        });
    }

    @PutMapping("/update/{recipeId}")
    public Recipe updateRecipe(@PathVariable(value = "userId") Long userId,
                               @PathVariable(value = "recipeId") Long recipeId,
                               @Valid @RequestBody Recipe recipeRequest) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException("not found");
        }

        return recipeService.findById(recipeId).map(recipe -> {
            recipe.setCookTime(recipeRequest.getCookTime());
            recipe.setDescription(recipeRequest.getDescription());
            recipe.setPrepareDescription(recipeRequest.getPrepareDescription());
            recipe.setPrepareTime(recipeRequest.getPrepareTime());
            recipe.setTitle(recipeRequest.getTitle());
            recipe.setIngredients(recipeRequest.getIngredients());
            return recipeService.save(recipe);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "userId") Long userId,
                                          @PathVariable(value = "recipeId") Long recipeId) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException("not found");
        }

        return recipeService.findById(recipeId).map(recipe -> {
            recipeService.delete(recipe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
