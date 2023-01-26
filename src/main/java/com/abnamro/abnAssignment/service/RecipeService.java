package com.abnamro.abnAssignment.service;

import com.abnamro.abnAssignment.controller.dto.RecipeSearchRequest;
import com.abnamro.abnAssignment.domain.Recipe;

import java.util.List;

public interface RecipeService {
     List<Recipe> getAllRecipes();
     Recipe getRecipeById(int id);
     Recipe  addRecipe(Recipe recipe);
     void deleteRecipe(int id);

     void updateRecipe(Recipe recipe);
     List<Recipe> findBySearchCriteria(RecipeSearchRequest searchCriteriaDto) throws Exception;

}
