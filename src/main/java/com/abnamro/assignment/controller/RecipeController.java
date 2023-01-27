package com.abnamro.assignment.controller;

import com.abnamro.assignment.controller.dto.RecipeRequestDto;
import com.abnamro.assignment.controller.dto.RecipeResponseDto;
import com.abnamro.assignment.controller.dto.RecipeSearchRequest;
import com.abnamro.assignment.domain.Recipe;
import com.abnamro.assignment.service.RecipeServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@Log
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
@Api(value = "Recipe manager", tags = " services are to add ,update , delete recipes and search recipes")
public class RecipeController {
    private final RecipeServiceImp recipeService;

    @PostMapping
    @ApiOperation(value="Adds new recipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeRequestDto recipeRequest) {
        log.info("Processing the request to create new recipe");
        Recipe recipe=new Recipe();
        BeanUtils.copyProperties(recipeRequest,recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.addRecipe(recipe));
    }

    @GetMapping(value = "/list-all-recipes")
    @ApiOperation(value="Lists id , name and cooking time of all available recipes ")
    public ResponseEntity<List<RecipeResponseDto>> listAllRecipes() {
        List<RecipeResponseDto> recipeResponseDtos = recipeService.getAllRecipes().stream().map(recipe -> RecipeResponseDto.builder().id(recipe.getId()).cookingTime(recipe.getCookingTime()).name(recipe.getName()).build()).toList();
        return ResponseEntity.ok().body(recipeResponseDtos);
    }


    @GetMapping("/{id}")
    @ApiOperation(value="Finds recipe by id ")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        log.info("Processing the request  to get existing recipe");
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping("/update")
    @ApiOperation(value="Modifies an existing recipe")
    public void modifyRecipe(@RequestBody RecipeRequestDto recipeRequest) {
        Recipe recipe=new Recipe();
        BeanUtils.copyProperties(recipeRequest,recipe);
        recipeService.updateRecipe(recipe);
        log.info("Service successfully modified existing recipe in DB");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Deletes an existing recipe")
    public void deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        log.info("Service successfully deleted existing recipe in DB");
    }

    @GetMapping("/search")
    @ApiOperation(value="Enables searching recipes")
    public ResponseEntity<List<Recipe>> searchRecipe(@ApiParam(value = "Properties of the the search") @RequestBody @Valid RecipeSearchRequest recipeSearchRequest) throws Exception {
        log.info("Searching the recipe by given criteria");
        return ResponseEntity.ok().body(recipeService.findBySearchCriteria(recipeSearchRequest));
    }

}