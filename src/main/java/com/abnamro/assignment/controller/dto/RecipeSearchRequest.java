package com.abnamro.assignment.controller.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@ApiModel(description = "Information about  list desired search criteria")
public class RecipeSearchRequest {
    @ApiModelProperty(notes = "Search criteria you want to search recipe with")
    @Valid
    private List<SearchCriteriaRequest> searchCriteriaRequests;

}
