package com.abnamro.abnAssignment.controller.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

public class RecipeSearchRequest {
    @JsonProperty("criteria")
    @ApiModelProperty(notes = "Search criteria you want to search recipe with")
    @Valid
    private List<SearchCriteriaRequest> searchCriteriaRequests;



    public RecipeSearchRequest() {
    }

    public RecipeSearchRequest(List<SearchCriteriaRequest> searchCriteriaRequests, String dataOption) {
        this.searchCriteriaRequests = searchCriteriaRequests;
    }

    public List<SearchCriteriaRequest> getSearchCriteriaRequests() {
        return searchCriteriaRequests;
    }



    public void setSearchCriteriaRequests(List<SearchCriteriaRequest> searchCriteriaRequests) {
        this.searchCriteriaRequests = searchCriteriaRequests;
    }
}
