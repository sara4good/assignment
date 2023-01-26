package com.abnamro.abnAssignment.controller.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchCriteriaRequest {

    @ApiModelProperty(notes = "The name of the column you want to search on available fields are " +
            "name, " +
            "numberOfServings, " +
            "type, " +
            "instructions, " +
            "ingredient)", example = "name")
    private String filterKey;


    @ApiModelProperty(notes = "The actual phrase you want to do search on", example = "Pasta")
    private Object value;

    @ApiModelProperty(notes = "The operation type you wanted to search (cn - contains, " +
            "nc - doesn't contain, " +
            "eq - equals, " +
            "ne - not equals", example = "cn")
    private String operation;






}
