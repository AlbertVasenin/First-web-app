package pro.sky.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipeapp.exeptions.ExceptionsApp;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD - операции с рецептами")
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @Operation(
      summary = "Создание рецепта",
      description = "Заполните поля в формате JSON")
  @PostMapping()
  public ResponseEntity<Long> addRecipe(@Valid @RequestBody Recipe recipe) throws ExceptionsApp {
    long id = recipeService.addRecipe(recipe);
    return ResponseEntity.ok(id);
  }

  @Operation(
      summary = "Получение рецепта",
      description = "Получение по ID(целочисленное число) рецепта")
  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable long id) throws ExceptionsApp {
    Recipe recipe = recipeService.getRecipe(id);
    if (recipe != null) {
      return ResponseEntity.ok(recipe);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Редактирование рецепта",
      description = "Редактирование по ID(целочисленное число) рецепта")
  @PutMapping("/{id}")
  public ResponseEntity<Recipe> editRecipe(@PathVariable long id,
      @RequestBody Recipe recipe) throws ExceptionsApp {
    Recipe recipes = recipeService.editRecipe(id, recipe);
    if (recipes != null) {
      return ResponseEntity.ok(recipes);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Удаление рецепта",
      description = "Удаление по ID(целочисленное число) рецепта")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecipe(@PathVariable long id) throws ExceptionsApp {
    if (recipeService.deleteRecipe(id)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Получение всего списка рецептов",
      description = "Входные данные не нужны")
  @GetMapping()
  public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
    Map<Long, Recipe> recipeList = recipeService.getAllRecipes();
    if (recipeList != null) {
      return ResponseEntity.ok(recipeList);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @Operation(
      summary = "Экспорт текстового списка рецептов",
      description = "Входные данные не нужны")
@GetMapping("/export")
  public ResponseEntity<Object> exportTxt() {
    try {
      Path recipesInTxt = recipeService.createRecipesInTxt();
      if (Files.size(recipesInTxt) == 0) {
        return ResponseEntity.noContent().build();
      }
      InputStreamResource resource = new InputStreamResource(
          new FileInputStream(recipesInTxt.toFile()));
      return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
          .contentLength(Files.size(recipesInTxt))
          .header(HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=" + "recipes.txt")
          .body(resource);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.toString());
    }
  }
}

