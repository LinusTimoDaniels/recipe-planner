package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.domain.Unit;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.UUID;

public class IngredientEntityMapperTest {

    private final IngredientEntityMapper mapper = Mappers.getMapper(IngredientEntityMapper.class);

    @Test
    void testEntityToDomain() {
        // Arrange (Vorbereitung)
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(UUID.randomUUID());
        ingredientEntity.setName("Sugar");
        ingredientEntity.setComment("Sweetener");
        ingredientEntity.setUnit(Unit.GRAMM);
        ingredientEntity.setAmount(200);

        // Act (Aktion)
        Ingredient ingredient = mapper.entityToDomain(ingredientEntity);

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getId()).isEqualTo(ingredientEntity.getId());
        softly.assertThat(ingredient.getName()).isEqualTo(ingredientEntity.getName());
        softly.assertThat(ingredient.getComment()).isEqualTo(ingredientEntity.getComment());
        softly.assertThat(ingredient.getUnit()).isEqualTo(ingredientEntity.getUnit());
        softly.assertThat(ingredient.getAmount()).isEqualTo(ingredientEntity.getAmount());
        softly.assertAll();
    }

    @Test
    void testDomainToEntity() {
        // Arrange (Vorbereitung)
        Ingredient ingredient = new Ingredient();
        ingredient.setId(UUID.randomUUID());
        ingredient.setName("Sugar");
        ingredient.setComment("Sweetener");
        ingredient.setUnit(Unit.GRAMM);
        ingredient.setAmount(200);

        // Act (Aktion)
        IngredientEntity ingredientEntity = mapper.domainToEntity(ingredient);

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredientEntity.getId()).isEqualTo(ingredient.getId());
        softly.assertThat(ingredientEntity.getName()).isEqualTo(ingredient.getName());
        softly.assertThat(ingredientEntity.getComment()).isEqualTo(ingredient.getComment());
        softly.assertThat(ingredientEntity.getUnit()).isEqualTo(ingredient.getUnit());
        softly.assertThat(ingredientEntity.getAmount()).isEqualTo(ingredient.getAmount());
        softly.assertAll();
    }

    @Test
    void testEntitiesToDomains() {
        // Arrange (Vorbereitung)
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(UUID.randomUUID());
        ingredientEntity.setName("Sugar");
        ingredientEntity.setComment("Sweetener");
        ingredientEntity.setUnit(Unit.GRAMM);
        ingredientEntity.setAmount(200);

        // Act (Aktion)
        var ingredients = mapper.entitiesToDomains(Collections.singletonList(ingredientEntity));

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredients).hasSize(1);
        softly.assertThat(ingredients.get(0).getId()).isEqualTo(ingredientEntity.getId());
        softly.assertThat(ingredients.get(0).getName()).isEqualTo(ingredientEntity.getName());
        softly.assertThat(ingredients.get(0).getComment()).isEqualTo(ingredientEntity.getComment());
        softly.assertThat(ingredients.get(0).getUnit()).isEqualTo(ingredientEntity.getUnit());
        softly.assertThat(ingredients.get(0).getAmount()).isEqualTo(ingredientEntity.getAmount());
        softly.assertAll();
    }

    @Test
    void testDomainsToEntities() {
        // Arrange (Vorbereitung)
        Ingredient ingredient = new Ingredient();
        ingredient.setId(UUID.randomUUID());
        ingredient.setName("Sugar");
        ingredient.setComment("Sweetener");
        ingredient.setUnit(Unit.GRAMM);
        ingredient.setAmount(200);

        // Act (Aktion)
        var ingredientEntities = mapper.domainsToEntities(Collections.singletonList(ingredient));

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredientEntities).hasSize(1);
        softly.assertThat(ingredientEntities.get(0).getId()).isEqualTo(ingredient.getId());
        softly.assertThat(ingredientEntities.get(0).getName()).isEqualTo(ingredient.getName());
        softly.assertThat(ingredientEntities.get(0).getComment()).isEqualTo(ingredient.getComment());
        softly.assertThat(ingredientEntities.get(0).getUnit()).isEqualTo(ingredient.getUnit());
        softly.assertThat(ingredientEntities.get(0).getAmount()).isEqualTo(ingredient.getAmount());
        softly.assertAll();
    }
}
