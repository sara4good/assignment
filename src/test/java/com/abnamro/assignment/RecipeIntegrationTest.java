package com.abnamro.assignment;

import com.abnamro.assignment.controller.dto.RecipeSearchRequest;
import com.abnamro.assignment.controller.dto.SearchCriteriaRequest;
import com.abnamro.assignment.domain.Ingredient;
import com.abnamro.assignment.domain.Recipe;
import com.abnamro.assignment.domain.RecipeType;
import com.abnamro.assignment.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Ignore
class RecipeIntegrationTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Recipe recipe1;
    private Set ingredients1 = new HashSet<Ingredient>();
    private Recipe recipe2;
    private Set ingredients2 = new HashSet<Ingredient>();

    @BeforeEach
    public void setup() {
        recipeRepository.deleteAll();
        recipe1 = Recipe.builder().recipeType(RecipeType.VEGETARIAN).name("kookooSabzi").cookingTime(10).instruction("stir fry sabzi and add egg").build();
        ingredients1.add(Ingredient.builder().name("sabzi").quantity(100).unit("grams").build());
        ingredients1.add(Ingredient.builder().name("egg").quantity(2).unit("number").build());
        recipe1.setIngredients(ingredients1);
        recipe2 = Recipe.builder().recipeType(RecipeType.OTHER).name("friedSalmon").cookingTime(10).instruction("fry salmon").build();
        ingredients1.add(Ingredient.builder().name("salmon").quantity(1).unit("grams").build());
        ingredients1.add(Ingredient.builder().name("oil").quantity(10).unit("grams").build());
    }

    @Test
     void should_createRecipe() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/recipe")
                        .content(objectMapper.writeValueAsString(recipe1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        Recipe recipe = objectMapper.readValue(json, Recipe.class);
        Optional<Recipe> searched = recipeRepository.findById(recipe.getId());
        assertThat(recipe1.getName())
                .isEqualTo(searched.get().getName());


    }


    @Test
     void should_find_recipe_by_id() throws Exception {
        Recipe saved = recipeRepository.save(recipe1);
        mockMvc.perform(get("/api/v1/recipe/" + saved.getId())).andExpect(status().isOk()).andExpect(jsonPath("name").value(recipe1.getName()))
                .andExpect(jsonPath("instruction").value(recipe1.getInstruction()))
                .andExpect(jsonPath("servings").value(recipe1.getServings())).andReturn();

    }

    @Test
      void should_set_not_found__for_finding_invalidId() throws Exception {

        mockMvc.perform(get("/api/v1/recipe/1")).andExpect(status().isNotFound());

    }

    @Test
      void should_get_list_of_recipes() throws Exception {

        List<Recipe> storedRecipeList = Arrays.asList(recipe1, recipe2);
        recipeRepository.saveAll(storedRecipeList);
        mockMvc.perform(get("/api/v1/recipe/list-all-recipes")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("kookooSabzi"))
                .andExpect(jsonPath("$[1].name").value("friedSalmon"));

    }

    @Test
      void should_update_recipe() throws Exception {

        Recipe savedRecipe = recipeRepository.save(recipe1);

        savedRecipe.setName("kookoo-sabzi");
        savedRecipe.setInstruction("mix flour with sabzi");

        mockMvc.perform(put("/api/v1/recipe/update/")
                        .content(objectMapper.writeValueAsString(savedRecipe))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Optional<Recipe> updatedRecipe = recipeRepository.findById(savedRecipe.getId());

        assertTrue(updatedRecipe.isPresent());
        assertEquals(savedRecipe.getName(), updatedRecipe.get().getName());
        assertEquals(savedRecipe.getInstruction(), updatedRecipe.get().getInstruction());
    }

    @Test
      void should_set_not_found_for_updaiting_invalidId() throws Exception {
        recipe1.setId(1);
        mockMvc.perform(put("/api/v1/recipe/update/")
                        .content(objectMapper.writeValueAsString(recipe1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
      void should_delete_existing_recipe() throws Exception {
        Recipe saved = recipeRepository.save(recipe1);

        mockMvc.perform(delete("/api/v1/recipe/" + saved.getId()))
                .andExpect(status().isOk());
        Optional<Recipe> deletedRecipe = recipeRepository.findById(saved.getId());

        assertTrue(deletedRecipe.isEmpty());
    }

    @Test
      void should_set_not_found_for_deleting_not_exisitng() throws Exception {
        mockMvc.perform(delete("/api/v1/recipe/1"))
                .andExpect(status().isNotFound());
    }

    @Test
      void test_SearchRecipeByCriteria_successfully() throws Exception {

        List<Recipe> storedRecipeList = Arrays.asList(recipe1, recipe2);
        recipeRepository.saveAll(storedRecipeList);
        RecipeSearchRequest request = new RecipeSearchRequest();
        List<SearchCriteriaRequest> searchCriteriaList = new ArrayList<>();
        SearchCriteriaRequest searchCriteria1 = new SearchCriteriaRequest("name",
                "kookoo",
                "cn");
        SearchCriteriaRequest searchCriteria2 = new SearchCriteriaRequest("ingredients",
                "egg",
                "cn");
        searchCriteriaList.add(searchCriteria1);
        searchCriteriaList.add(searchCriteria2);

        request.setSearchCriteriaRequests(searchCriteriaList);
        mockMvc.perform(get("/api/v1/recipe/search")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("kookooSabzi"));

    }


}

