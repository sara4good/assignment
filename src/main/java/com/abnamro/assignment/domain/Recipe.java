package com.abnamro.assignment.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * This class represents a recipe,
 * Each recipe can have multiple ingredients , so ther is a on to many reationship between recipe and ingredient
 */
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String instruction;
    private Integer cookingTime;
    private Integer servings;
    private RecipeType recipeType;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();


}
