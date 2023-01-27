package com.abnamro.assignment.repository;

import com.abnamro.assignment.domain.Recipe;
import com.abnamro.assignment.domain.RecipeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void shoulid_successfully_save_recipe() {
        Recipe entity = new Recipe();
        RecipeType.VEGETARIAN.name();
        entity.setRecipeType(RecipeType.valueOf("OTHER"));
        entity.setInstruction("some instructions");
        entity.setName("test");
        Recipe savedRecipe = recipeRepository.save(entity);
        assertNotNull(savedRecipe);
        assertEquals("OTHER", savedRecipe.getRecipeType().toString());
        assertNotNull(savedRecipe.getId());
    }

    @Test
    public void test_whenTryGetTokenListSuccess() {
        Recipe entity1 = new Recipe();
        entity1.setRecipeType(RecipeType.valueOf("VEGETARIAN"));
        entity1.setName("test1");

        Recipe entity2 = new Recipe();
        entity2.setRecipeType(RecipeType.valueOf("OTHER"));
        entity2.setName("test2");


        Recipe firstSavedEntity = recipeRepository.save(entity1);
        Recipe secondSavedEntity = recipeRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(recipeRepository.findAll().isEmpty());
        assertEquals(2, recipeRepository.findAll().size());
    }

}

