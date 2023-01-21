package com.abnamro.abnAssignment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String instruction;
    private Integer cookingTime;
    private Integer servings;
    private RecipeType recipeType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
