package com.abnamro.abnAssignment.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
