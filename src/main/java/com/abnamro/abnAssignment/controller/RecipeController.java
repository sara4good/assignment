package com.abnamro.abnAssignment.controller;

import com.abnamro.abnAssignment.controller.dto.RecipeResponseDto;
import com.abnamro.abnAssignment.controller.dto.RecipeSearchRequest;
import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.service.RecipeServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RestController
@Log
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
@Api(value = "Recipe manager", tags = "These services are to add ,update , delete recipes")
public class RecipeController {
    private final RecipeServiceImp recipeService;

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(
            @RequestBody Recipe recipe) {
        log.info("Processing the request for /api/recipe to create new recipe");

        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.addRecipe(recipe));
    }

    @GetMapping(value = "/list-all-recipes")
    public ResponseEntity<List<RecipeResponseDto>> listAllRecipe() {
        List<RecipeResponseDto> recipeResponseDtos = recipeService.getAllRecipes().stream()
                .map(recipe -> RecipeResponseDto.builder()
                        .id(recipe.getId())
                        .cookingTime(recipe.getCookingTime())
                        .name(recipe.getName()).build())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(recipeResponseDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(
            @PathVariable Integer id) {
        log.info("Processing the request for /api/recipe/id to get existing recipe");
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok().body(recipe);

    }

    @PutMapping("/update")
    public ResponseEntity modifyRecipe(
            @RequestBody Recipe recipe) {
        recipeService.updateRecipe(recipe);
        log.info("Service successfully modifed existing recipe in DB");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe(
            @PathVariable Integer id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.OK).build();
        }
    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public ResponseEntity<List<Recipe>> searchRecipe(
            @ApiParam(value = "Properties of the the search")
                                             @RequestBody @Valid RecipeSearchRequest recipeSearchRequest) throws Exception {
        log.info("Searching the recipe by given criteria");
        return ResponseEntity.ok().body(recipeService.findBySearchCriteria(recipeSearchRequest));
    }

    }




