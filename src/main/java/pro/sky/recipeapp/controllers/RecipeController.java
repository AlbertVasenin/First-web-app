package pro.sky.recipeapp.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/save")
  public String saveRecipe(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "time", required = false) int time,
      @RequestParam(value = "ingredients_title", required = false) String ingredient,
      @RequestParam(value = "ingredients_count", required = false) int count,
      @RequestParam(value = "ingredients_measure", required = false) String measure,
      @RequestParam(value = "instruction", required = false) List<String> instruction) {
    Ingredients ingredients = new Ingredients(ingredient, count, measure);
    Recipe recipe = new Recipe();
    recipe.setName(name);
    recipe.setTime(time);
    recipe.saveIngredient(ingredients);
    recipe.setIngredientsList(ingredients);
    recipe.setInstruction(instruction);
    recipeService.saveRecipe(recipe);
    return "Рецепт: " + "\"" + name + "\"" + " добавлен," + " ингредиенты рецепта: "
        + recipe.showListIngredientsInRecipeId();
  }

  @GetMapping("/{id}")
  public String getRecipe(@PathVariable(value = "id", required = false) int id) {
    if (recipeService.getRecipe(id) != null)
      ;
    return recipeService.getRecipe(id);
  }
}
