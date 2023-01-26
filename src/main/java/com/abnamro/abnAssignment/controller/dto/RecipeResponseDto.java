package com.abnamro.abnAssignment.controller.dto;

import com.abnamro.abnAssignment.domain.Recipe;
import lombok.*;

import java.util.List;
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseDto {

    private Integer id;
    private String name;
    private Integer cookingTime;

}
