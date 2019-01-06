package com.marcin.demo;

import com.marcin.demo.image.service.ImageService;
import com.marcin.demo.ingredients.service.IngredientsService;
import com.marcin.demo.model.Image;
import com.marcin.demo.model.Ingredient;
import com.marcin.demo.model.Recipe;
import com.marcin.demo.model.RecipeStep;
import com.marcin.demo.recipe.service.RecipeService;
import com.marcin.demo.recipe_step.service.RecipeStepsService;
import com.marcin.demo.user.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    ApplicationRunner init(/*CarRepository repository, */UserService userService, RecipeService recipeService, IngredientsService ingredientsService, RecipeStepsService recipeStepsService, ImageService imageService) {
        return args -> {
            /*User user = new User();
            user.setName("marcin");
            user.setEmail("marcin.uryga@onet.eu");
            userService.save(user);*/


            Recipe recipe = new Recipe();
            recipe.setTitle("Spaghetti");
            recipe.setDescription("This recipe has been handed down from my mother. It is a family favorite and will not be replaced!");
            recipe.setPrepareTime("15min");
            recipe.setCookTime("1h 10min");
//            recipe.setUser(user);
            recipeService.save(recipe);

            List<Ingredient> ingredients = mockIngredients(recipe);
            ingredients.forEach(ingredientsService::save);

            List<RecipeStep> recipeSteps = mockSteps(recipe);
            recipe.setRecipeSteps(recipeSteps);
            recipeSteps.forEach(recipeStepsService::save);

            List<Image> images = mockImages(recipe);
            recipe.setImages(mockImages(recipe));
            images.forEach(imageService::save);

//            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
//                    "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
//                Car car = new Car();
//                car.setName(name);
//                repository.save(car);
//            });
//            repository.findAll().forEach(System.out::println);
        };
    }

    private List<Image> mockImages(Recipe recipe) {
        Image image = new Image();
        image.setImagesUrl("https://images.media-allrecipes.com/userphotos/720x405/667748.jpg");
        image.setRecipe(recipe);
        return Collections.singletonList(image);
    }

    private List<RecipeStep> mockSteps(Recipe recipe) {
        RecipeStep firstStep = new RecipeStep();
        firstStep.setStepNumber(1);
        firstStep.setName("Combine ground beef, onion, garlic, and green pepper in a large saucepan. Cook and stir until meat is brown and vegetables are tender. Drain grease.");
        firstStep.setRecipe(recipe);

        RecipeStep secondStep = new RecipeStep();
        secondStep.setStepNumber(2);
        secondStep.setName("Stir diced tomatoes, tomato sauce, and tomato paste into the pan. Season with oregano, basil, salt, and pepper. Simmer spaghetti sauce for 1 hour, stirring occasionally.");
        secondStep.setRecipe(recipe);

        return Arrays.asList(
                firstStep,
                secondStep
        );
    }

    private List<Ingredient> mockIngredients(Recipe recipe) {
        Ingredient groundBeef = new Ingredient();
        groundBeef.setName("Ground beef");
        groundBeef.setQuantity("1 pound");
        groundBeef.setRecipe(recipe);

        Ingredient onion = new Ingredient();
        onion.setName("chopped onion");
        onion.setQuantity("1");
        onion.setRecipe(recipe);

        Ingredient garlic = new Ingredient();
        garlic.setName("cloves garlic, minced");
        garlic.setQuantity("4");
        garlic.setRecipe(recipe);

        Ingredient pepper = new Ingredient();
        pepper.setName("small green bell pepper, diced");
        pepper.setQuantity("1");
        pepper.setRecipe(recipe);

        Ingredient tomatoes = new Ingredient();
        tomatoes.setName("can diced tomatoes");
        tomatoes.setQuantity("1 (28 ounce)");
        tomatoes.setRecipe(recipe);

        Ingredient tomatoSauce = new Ingredient();
        tomatoSauce.setName("can tomato sauce");
        tomatoSauce.setQuantity("1 (16 ounce)");
        tomatoSauce.setRecipe(recipe);

        Ingredient tomatoPaste = new Ingredient();
        tomatoPaste.setName("can tomato paste");
        tomatoPaste.setQuantity("1 (6 ounce)");
        tomatoPaste.setRecipe(recipe);

        Ingredient oregano = new Ingredient();
        oregano.setName("dried oregano");
        oregano.setQuantity("2 teaspoons");
        oregano.setRecipe(recipe);

        Ingredient basil = new Ingredient();
        basil.setName("dried basil");
        basil.setQuantity("2 teaspoons");
        basil.setRecipe(recipe);

        Ingredient salt = new Ingredient();
        salt.setName("salt");
        salt.setQuantity("1 teaspoon");
        salt.setRecipe(recipe);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setName("black pepper");
        blackPepper.setQuantity("1/2 teaspoon");
        blackPepper.setRecipe(recipe);

        return Arrays.asList(
                groundBeef,
                onion,
                garlic,
                pepper,
                tomatoes,
                tomatoPaste,
                tomatoSauce,
                oregano,
                basil,
                salt,
                blackPepper
        );
    }
}

