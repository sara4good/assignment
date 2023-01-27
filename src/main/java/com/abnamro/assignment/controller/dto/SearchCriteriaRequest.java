package com.abnamro.assignment.controller.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ApiModel(description = "Search criteria specification")
public class SearchCriteriaRequest {

    @ApiModelProperty(notes = "The name of the column you want to search on available fields are " + "name, " + "servings, " + "recipeType, " + "instruction, " + "ingredients)", example = "name")
    @NotNull(message = "filter key is required")
    private String filterKey;


    @ApiModelProperty(notes = "The actual phrase you want to do search on", example = "Pasta")
    @NotNull(message = " value is required")
    private Object value;

    @ApiModelProperty(notes = "The operation type you wanted to search (cn - contains, " + "nc - doesn't contain, " + "eq - equals, " + "ne - not equals", example = "cn")
    @NotNull(message = "Operation is required")
    private String operation;


}
