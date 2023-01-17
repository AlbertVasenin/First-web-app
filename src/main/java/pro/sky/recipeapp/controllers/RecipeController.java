package pro.sky.recipeapp.controllers;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @PostMapping()
  public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe) {
    long id = recipeService.addRecipe(recipe);
    return ResponseEntity.ok(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
    Recipe recipe = recipeService.getRecipe(id);
    if (recipe != null) {
      return ResponseEntity.ok(recipe);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Recipe> editRecipe(@PathVariable long id,
      @RequestBody Recipe recipe) {
    Recipe recipes = recipeService.editRecipe(id, recipe);
    if (recipes != null) {
      return ResponseEntity.ok(recipes);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
    if (recipeService.deleteRecipe(id)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping()
  public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
    Map<Long, Recipe> recipeList = recipeService.getAllRecipes();
    if (recipeList != null) {
      return ResponseEntity.ok(recipeList);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
