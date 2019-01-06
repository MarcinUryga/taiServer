package com.marcin.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private String description;
    @Getter @Setter private String cookTime;
    @Getter @Setter private String prepareTime;
    @Getter @Setter private String prepareDescription;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    @Getter @Setter private User user;*/

    @OneToMany(mappedBy = "recipe")
    @Getter @Setter private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe")
    @Getter @Setter private List<Image> images;

    @OneToMany(mappedBy = "recipe")
    @Getter @Setter private List<RecipeStep> recipeSteps;
}