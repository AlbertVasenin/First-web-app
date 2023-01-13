package pro.sky.recipeapp.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

  private RecipeService recipeService;


  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/add")
  public String addRecipe(@RequestParam("name") String name,
      @RequestParam("time") int time,
      @RequestParam("ingredients") List<String> ingredients,
      @RequestParam("instruction") List<String> instruction) {
    recipeService.addRecipe(name, time, ingredients, instruction);
    return "Рецепт " + name + " добавлен";
  }

  @GetMapping("/{id}")
  public String getRecipe(@PathVariable("id") int id) {
    if (recipeService.getRecipe(id) != null)
      ;
    return recipeService.getRecipe(id);
  }
}
