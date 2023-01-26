package com.abnamro.abnAssignment.service;

import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.exceptions.NotFoundException;
import com.abnamro.abnAssignment.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImpTest {
    @Mock
    RecipeRepository repository;

    @InjectMocks
    RecipeServiceImp service;

    @Test
    void getAllRecipes() {
        Recipe recipe1=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        Recipe recipe2=Recipe.builder()
                .name("fesenjoon")
                .instruction("instruction")
                .build();
        List<Recipe>expected=Arrays.asList(recipe1,recipe2);
        given(repository.findAll())
                .willReturn(expected);

        List<Recipe> actual=service.getAllRecipes();
        then(actual).isEqualTo(expected);

    }

    @Test
    void getRecipeById() throws Exception{
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        given(repository.findById(any()))
                .willReturn(Optional.ofNullable(expected));

        Recipe actual=service.getRecipeById(1);
        then(actual).isEqualTo(expected);
    }

    @Test
    void addRecipe() {
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        given(repository.save(any()))
                .willReturn(expected);
        Recipe actual=service.addRecipe(expected);
        then(actual).isEqualTo(expected);
    }

    @Test
    void updateRecipe() {
        Recipe expected=Recipe.builder()
                .id(1)
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        when(repository.save(any()))
                .thenReturn(expected);
        when(repository.existsById(any()))
                .thenReturn(true);

        service.updateRecipe(expected);
        given(repository.existsById(any()))
                .willReturn(false);
        assertThatThrownBy( () -> this.service.updateRecipe(expected))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void deleteRecipe() {
        doNothing().when(repository).deleteById(1);
        given(repository.existsById(any()))
                .willReturn(true);
        service.deleteRecipe(1);
        given(repository.existsById(any()))
                .willReturn(false);
        assertThatThrownBy( () -> service.deleteRecipe(1))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void findBySearchCriteria() {
        Recipe expected=Recipe.builder()
                .name("ghormeSabzi")
                .instruction("instruction")
                .build();
        given(repository.save(any()))
                .willReturn(expected);
        Recipe actual=service.addRecipe(expected);
        then(actual).isEqualTo(expected);
    }
}