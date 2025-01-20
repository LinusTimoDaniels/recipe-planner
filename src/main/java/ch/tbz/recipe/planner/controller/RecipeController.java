package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j

@CrossOrigin(origins = {"*"})
public class RecipeController {

    private final RecipeService service;

    private final RecipeEntityMapper mapper;

    public RecipeController(RecipeService service, RecipeEntityMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/api/recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        return new ResponseEntity<>(service.getRecipes(), HttpStatus.OK);
    }

    @GetMapping("/api/recipes/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable UUID recipeId) {
        return new ResponseEntity<>(service.getRecipeById(recipeId), HttpStatus.OK);
    }

    @PostMapping(value = "/api/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = service.addRecipe(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.OK);
    }

    @PutMapping(value = "/api/recipes/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeEntity> updateRecipe(@PathVariable UUID recipeId, @RequestBody Recipe recipe) throws NoSuchObjectException {
        RecipeEntity updatedRecipe = service.updateRecipe(recipeId, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

}