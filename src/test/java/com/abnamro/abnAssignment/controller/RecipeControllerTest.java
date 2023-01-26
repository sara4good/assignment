package com.abnamro.abnAssignment.controller;

import com.abnamro.abnAssignment.controller.dto.RecipeSearchRequest;
import com.abnamro.abnAssignment.controller.dto.SearchCriteriaRequest;
import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.service.RecipeServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private RecipeServiceImp recipeService;




        @Test
    void addRecipe() throws Exception {
            Recipe recipe=Recipe.builder()
                    .name("ghormeSabzi")
                    .instruction("instruction")
                    .build();
            given(recipeService.addRecipe(any()))
                    .willReturn(recipe);
           mockMvc.perform(post("/api/v1/recipe")
                            .content(objectMapper.writeValueAsString(recipe))
                            .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(jsonPath("instruction").value(recipe.getInstruction()));



        }

    @Test
    void listAllRecipe() throws Exception {
        Recipe recipe1=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        Recipe recipe2=Recipe.builder()
                .name("fesenjoon")
                .instruction("instruction")
                .build();
        List<Recipe> expected= Arrays.asList(recipe1,recipe2);
        given(recipeService.getAllRecipes())
                .willReturn(expected);
        mockMvc.perform(get("/api/v1/recipe/list-all-recipes")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("ghormeSabzi"))
                .andExpect(jsonPath("$[1].name").value("fesenjoon"));

    }

    @Test
    void getRecipe() throws Exception {
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        given(recipeService.getRecipeById(1))
                .willReturn(expected);
        mockMvc.perform(get("/api/v1/recipe/1" )).andExpect(status().isOk()).andExpect(jsonPath("name").value(expected.getName()))
                .andExpect(jsonPath("instruction").value(expected.getInstruction()))
                .andExpect(jsonPath("servings").value(expected.getServings())).andReturn();
    }

    @Test
    void modifyRecipe() throws Exception {
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        doNothing().when(recipeService).updateRecipe(any());

        mockMvc.perform(put("/api/v1/recipe/update/")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRecipe() throws Exception {
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();

        mockMvc.perform(delete("/api/v1/recipe/1")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void searchRecipe() throws Exception{
        Recipe recipe1=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        Recipe recipe2=Recipe.builder()
                .name("fesenjoon")
                .instruction("instruction")
                .build();
        List<Recipe> expected= Arrays.asList(recipe1,recipe2);
        RecipeSearchRequest request = new RecipeSearchRequest();
        List<SearchCriteriaRequest> searchCriteriaList = new ArrayList<>();
        SearchCriteriaRequest searchCriteria1 = new SearchCriteriaRequest("name",
                "ghormeSabzi",
                "cn");


        request.setSearchCriteriaRequests(searchCriteriaList);
        given(recipeService.findBySearchCriteria(any()))
                .willReturn(expected);
        mockMvc.perform(get("/api/v1/recipe/search")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ghormeSabzi"));
    }
}