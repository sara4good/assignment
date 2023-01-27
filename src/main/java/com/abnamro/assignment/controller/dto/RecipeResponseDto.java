package com.abnamro.assignment.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Brief information of recipe")
public class RecipeResponseDto {
    @ApiModelProperty(notes = "Id of the recipe")
    private Integer id;
    @ApiModelProperty(notes = "Name of the recipe")
    private String name;
    @ApiModelProperty(notes = "Cooking time of the recipe")
    private Integer cookingTime;

}
