package pro.sky.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Ингредиенты", description = "CRUD - операции с ингредиентами")
public class IngredientsController {

  private IngredientsService ingredientsService;

  public IngredientsController(IngredientsService ingredientsService) {
    this.ingredientsService = ingredientsService;
  }

  @Operation(
      summary = "Создание ингредиента",
      description = "Заполните поля в формате JSON")
  @PostMapping()
  public ResponseEntity<Long> addIngredients(@RequestBody Ingredients ingredients) {
    long id = ingredientsService.addIngredients(ingredients);
    return ResponseEntity.ok(id);
  }

  @Operation(
      summary = "Получение ингредиента",
      description = "Получение по ID(целочисленное число) ингредиента")
  @GetMapping("/{id}")
  public ResponseEntity<Ingredients> getIngredients(@PathVariable long id) {
    Ingredients ingredients = ingredientsService.getIngredients(id);
    if (ingredients != null) {
      return ResponseEntity.ok(ingredients);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Редактирование ингредиента",
      description = "Редактирование по ID(целочисленное число) ингредиента")
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

  @Operation(
      summary = "Удаление ингредиента",
      description = "Удаление по ID(целочисленное число) ингредиента")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
    if (ingredientsService.deleteIngredient(id)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Получение всего списка ингредиентов",
      description = "Входные данные не нужны")
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



