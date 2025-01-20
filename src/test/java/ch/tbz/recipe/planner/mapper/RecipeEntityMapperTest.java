package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.UUID;

public class RecipeEntityMapperTest {

    private final RecipeEntityMapper mapper = Mappers.getMapper(RecipeEntityMapper.class);

    @Test
    void testEntityToDomain() {
        // Arrange (Vorbereitung)
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(UUID.randomUUID());
        recipeEntity.setName("Recipe 1");
        recipeEntity.setDescription("Delicious recipe");
        recipeEntity.setImageUrl("http://example.com/image.jpg");
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(UUID.randomUUID());
        ingredientEntity.setName("Sugar");
        ingredientEntity.setAmount(200);
        recipeEntity.setIngredients(Arrays.asList(ingredientEntity));

        // Act (Aktion)
        Recipe recipe = mapper.entityToDomain(recipeEntity);

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipe.getId()).isEqualTo(recipeEntity.getId());
        softly.assertThat(recipe.getName()).isEqualTo(recipeEntity.getName());
        softly.assertThat(recipe.getDescription()).isEqualTo(recipeEntity.getDescription());
        softly.assertThat(recipe.getImageUrl()).isEqualTo(recipeEntity.getImageUrl());
        softly.assertThat(recipe.getIngredients()).hasSize(1);
        softly.assertThat(recipe.getIngredients().get(0).getName()).isEqualTo("Sugar");
        softly.assertThat(recipe.getIngredients().get(0).getAmount()).isEqualTo(200);
        softly.assertAll();
    }

    @Test
    void testDomainToEntity() {
        // Arrange (Vorbereitung)
        Recipe recipe = new Recipe();
        recipe.setId(UUID.randomUUID());
        recipe.setName("Recipe 1");
        recipe.setDescription("Delicious recipe");
        recipe.setImageUrl("http://example.com/image.jpg");
        Ingredient ingredient = new Ingredient();
        ingredient.setId(UUID.randomUUID());
        ingredient.setName("Sugar");
        ingredient.setAmount(200);
        recipe.setIngredients(Arrays.asList(ingredient));

        // Act (Aktion)
        RecipeEntity recipeEntity = mapper.domainToEntity(recipe);

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipeEntity.getId()).isEqualTo(recipe.getId());
        softly.assertThat(recipeEntity.getName()).isEqualTo(recipe.getName());
        softly.assertThat(recipeEntity.getDescription()).isEqualTo(recipe.getDescription());
        softly.assertThat(recipeEntity.getImageUrl()).isEqualTo(recipe.getImageUrl());
        softly.assertThat(recipeEntity.getIngredients()).hasSize(1);
        softly.assertThat(recipeEntity.getIngredients().get(0).getName()).isEqualTo("Sugar");
        softly.assertThat(recipeEntity.getIngredients().get(0).getAmount()).isEqualTo(200);
        softly.assertAll();
    }
}
