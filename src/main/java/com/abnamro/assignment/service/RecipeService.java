package com.abnamro.assignment.service;

import com.abnamro.assignment.controller.dto.RecipeSearchRequest;
import com.abnamro.assignment.domain.Recipe;

import java.util.List;

public interface RecipeService {

    /**
     * retrieve all recipes
     *
     * @return list of Recipes
     */
    List<Recipe> getAllRecipes();

    /**
     * retrieve the recipe with the given Id
     *
     * @param id , id of the recipe to be searched
     * @return Recipe
     */
    Recipe getRecipeById(int id);

    /**
     * add a new recipe
     *
     * @param recipe
     * @return saved recipe
     */
    Recipe addRecipe(Recipe recipe);

    /**
     * delete recipe by a given id
     *
     * @param id of the recipe to be deleted
     */
    void deleteRecipe(int id);

    /**
     * update recipe
     *
     * @param recipe to be updated
     */
    void updateRecipe(Recipe recipe);

    /**
     * search recipes based on specific criteria
     *
     * @param searchCriteriaDto to  search base on
     * @return list of recipes
     */
    List<Recipe> findBySearchCriteria(RecipeSearchRequest searchCriteriaDto);

}
