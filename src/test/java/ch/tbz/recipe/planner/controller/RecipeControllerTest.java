package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService service;

    @InjectMocks
    private RecipeController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Stelle sicher, dass Mocks korrekt initialisiert sind
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetRecipes() throws Exception {
        // Arrange (Vorbereitung)
        Recipe recipe = new Recipe(UUID.randomUUID(), "Test Recipe", "Description", "http://example.com/image.jpg", List.of());
        when(service.getRecipes()).thenReturn(List.of(recipe));

        // Act (Aktion)
        var result = mockMvc.perform(get("/api/recipes"));

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipe.getName()).isEqualTo("Test Recipe");
        softly.assertThat(recipe.getDescription()).isEqualTo("Description");
        softly.assertThat(recipe.getImageUrl()).isEqualTo("http://example.com/image.jpg");
        softly.assertAll();

        // Überprüfen, dass der Service nur einmal aufgerufen wird
        verify(service, times(1)).getRecipes();
    }

    @Test
    void testGetRecipeById() throws Exception {
        // Arrange (Vorbereitung)
        UUID recipeId = UUID.randomUUID();
        Recipe recipe = new Recipe(recipeId, "Test Recipe", "Description", "http://example.com/image.jpg", List.of());
        when(service.getRecipeById(recipeId)).thenReturn(recipe);

        // Act (Aktion)
        var result = mockMvc.perform(get("/api/recipes/recipe/{recipeId}", recipeId));

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipe.getName()).isEqualTo("Test Recipe");
        softly.assertThat(recipe.getDescription()).isEqualTo("Description");
        softly.assertThat(recipe.getImageUrl()).isEqualTo("http://example.com/image.jpg");
        softly.assertAll();

        // Überprüfen, dass der Service nur einmal aufgerufen wird
        verify(service, times(1)).getRecipeById(recipeId);
    }

    @Test
    void testAddRecipe() throws Exception {
        // Arrange (Vorbereitung)
        Recipe recipe = new Recipe(UUID.randomUUID(), "New Recipe", "New Description", "http://example.com/newimage.jpg", List.of());
        when(service.addRecipe(any(Recipe.class))).thenReturn(recipe);

        // Act (Aktion)
        var result = mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(recipe)));

        // Soft Assertions durchführen
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipe.getName()).isEqualTo("New Recipe");
        softly.assertThat(recipe.getDescription()).isEqualTo("New Description");
        softly.assertThat(recipe.getImageUrl()).isEqualTo("http://example.com/newimage.jpg");
        softly.assertAll();

        // Überprüfen, dass der Service nur einmal aufgerufen wird
        verify(service, times(1)).addRecipe(any(Recipe.class));
    }
}
