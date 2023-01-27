package com.abnamro.assignment.controller.dto;

import com.abnamro.assignment.config.ValidationConfig;
import com.abnamro.assignment.domain.Ingredient;
import com.abnamro.assignment.domain.RecipeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = " information of the recipe request ")
public class RecipeRequestDto {
    @NotNull(message = "id cant be null")
    private Integer id;

    @NotBlank(message = "recipe cant be blank")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "recipe name cant be more than 100 characters")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "recipe name is not in a valid pattern")
    @ApiModelProperty(notes = "The name of the recipe", example = "Ghorme Sabzi")
    private String name;

    @ApiModelProperty(notes = "The type of the recipe", example = "VEGETARIAN")
    private RecipeType recipeType;

    @NotNull(message = "number of serving cant be null")
    @Positive(message = "cooking time should be positive")
    @ApiModelProperty(notes = "The number of servings per recipe", example = "4")
    private Integer servings;

    @ApiModelProperty(notes = "The  ingredients of the recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @NotBlank(message = "instruction cant be blank")
    @Size(max = ValidationConfig.MAX_LENGTH_DEFAULT, message = "instruction size is not valid")
    @Pattern(regexp = ValidationConfig.PATTERN_FREE_TEXT, message = "instructio is not in a valid pattern")
    @ApiModelProperty(notes = "The instructions to create the recipe")
    private String instruction;

    @NotNull(message = "cookingTime cant be null")
    @Positive(message = "cooking time should be positive")
    @ApiModelProperty(notes = "The number of servings per recipe", example = "4")
    private Integer cookingTime;


}
