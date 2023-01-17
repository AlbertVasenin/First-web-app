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
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.IngredientsService;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

  private IngredientsService ingredientsService;

  public IngredientsController(IngredientsService ingredientsService) {
    this.ingredientsService = ingredientsService;
  }

  @PostMapping()
  public ResponseEntity<Long> addIngredients(@RequestBody Ingredients ingredients) {
    long id = ingredientsService.addIngredients(ingredients);
    return ResponseEntity.ok(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredients> getIngredients(@PathVariable long id) {
    Ingredients ingredients = ingredientsService.getIngredients(id);
    if (ingredients != null) {
      return ResponseEntity.ok(ingredients);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Ingredients> editIngredients(@PathVariable long id,
      @RequestBody Ingredients ingredients) {
    Ingredients ingredient = ingredientsService.editIngredients(id, ingredients);
    if (ingredient != null) {
      return ResponseEntity.ok(ingredient);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
    if (ingredientsService.deleteIngredient(id)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping()
  public ResponseEntity<Map<Long, Ingredients>> getAllIngredients() {
    Map<Long, Ingredients> ingredientsList = ingredientsService.getAllIngredients();
    if (ingredientsList != null) {
      return ResponseEntity.ok(ingredientsList);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}



