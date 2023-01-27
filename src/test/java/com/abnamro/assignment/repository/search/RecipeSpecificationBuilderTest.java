package com.abnamro.assignment.repository.search;

import com.abnamro.assignment.domain.Recipe;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecipeSpecificationBuilderTest {
    List<SearchCriteria> params;

    public RecipeSpecificationBuilderTest() {
        params = new ArrayList<>();
    }

    @Test
    public void test_buildMethodWhenParamsIsEmpty_successfully() {
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(params);
        Optional<Specification<Recipe>> build = builder.build();
        assertEquals(Optional.empty(), build);
    }

    @Test
    public void test_buildMethodWhenParamsIsNotEmpty_successfully() {
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(params);
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFilterKey("name");
        searchCriteria.setOperation("cn");
        searchCriteria.setValue("pasta");
        params.add(searchCriteria);
        Optional<Specification<Recipe>> build = builder.build();
        assertTrue(build.isPresent());
     }

}
