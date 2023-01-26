package com.abnamro.abnAssignment.service;

import com.abnamro.abnAssignment.controller.dto.RecipeSearchRequest;
import com.abnamro.abnAssignment.controller.dto.SearchCriteriaRequest;
import com.abnamro.abnAssignment.domain.Recipe;
import com.abnamro.abnAssignment.exceptions.NotFoundException;
import com.abnamro.abnAssignment.repository.RecipeRepository;
import com.abnamro.abnAssignment.repository.search.RecipeSpecificationBuilder;
import com.abnamro.abnAssignment.repository.search.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecipeServiceImp implements RecipeService {
    private final RecipeRepository recipeRepository;


    @Override
    public List<Recipe> getAllRecipes() {

       return recipeRepository.findAll();
    }


    @Override
    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("recipe notFound"));
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipe(Recipe updateRecipe) {
        if (!recipeRepository.existsById(updateRecipe.getId())) {
            throw new NotFoundException("recipe with this id not Found");
        }
        recipeRepository.save(updateRecipe);
    }
    @Override
    public void deleteRecipe(int id) {
        if (!recipeRepository.existsById(id)) {
            throw new NotFoundException("recipe with this id not Found");
        }

        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findBySearchCriteria(RecipeSearchRequest recipeSearchRequest) throws Exception {
        List<SearchCriteria> searchCriterionRequests = new ArrayList<>();
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(searchCriterionRequests);

        Specification<Recipe> recipeSpecification = createRecipeSpecification(recipeSearchRequest, builder);
        return recipeRepository.findAll(recipeSpecification);

    }

    private Specification<Recipe> createRecipeSpecification(RecipeSearchRequest recipeSearchRequest,
                                                            RecipeSpecificationBuilder builder) throws Exception {
        List<SearchCriteriaRequest> searchCriteriaRequests = recipeSearchRequest.getSearchCriteriaRequests();

        if (Optional.ofNullable(searchCriteriaRequests).isPresent()) {
            List<SearchCriteria> searchCriteriaList = searchCriteriaRequests.stream()
                    .map(SearchCriteria::new)
                    .collect(Collectors.toList());

            if (!searchCriteriaList.isEmpty()) searchCriteriaList.forEach(criteria -> {
                builder.with(criteria);
            });
        }

        return builder
                .build()
                .orElseThrow(() -> new Exception());
    }

}
