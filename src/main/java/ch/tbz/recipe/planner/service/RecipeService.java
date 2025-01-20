package ch.tbz.recipe.planner.service;

import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.domain.Recipe;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    RecipeEntityMapper mapper;

    RecipeRepository repository;

    public RecipeService(RecipeEntityMapper mapper, RecipeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<Recipe> getRecipes() {
        List<RecipeEntity> recipesEntities = repository.findAll();
        return recipesEntities.stream().map(mapper::entityToDomain).toList();
    }

    public Recipe getRecipeById(UUID recipeId) {
        return mapper.entityToDomain(repository.findById(recipeId).orElse(null));
    }

    public Recipe addRecipe(Recipe recipe) {
        var createdRecipe = repository.save(mapper.domainToEntity(recipe));
        return mapper.entityToDomain(createdRecipe);
    }

    public RecipeEntity updateRecipe(UUID recipeId, Recipe recipe) throws NoSuchObjectException {
        // Fetch the existing recipe from the repository
        RecipeEntity existingRecipe = repository.findById(recipeId)
                .orElseThrow(() -> new NoSuchObjectException("Recipe not found with id " + recipeId));

        // Update the existing recipe with new values
        existingRecipe.setName(recipe.getName());
        existingRecipe.setDescription(recipe.getDescription());
        existingRecipe.setImageUrl(recipe.getImageUrl());

        // Save the updated recipe back to the repository
        return repository.save(existingRecipe);
    }

}