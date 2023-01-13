package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recipeapp.services.IngredientsService;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

  private IngredientsService ingredientsService;

  public IngredientsController(IngredientsService ingredientsService) {
    this.ingredientsService = ingredientsService;
  }

  @GetMapping("/add")
  public String addIngredients(@RequestParam("title") String title,
      @RequestParam("count") int count,
      @RequestParam("measure") String measure) {
    ingredientsService.addIngredients(title, count, measure);
    return "Ингредиент " + title + " " + count + " " + measure + " добавлен";
  }

  @GetMapping("/{id}")
  public String getIngredients(@PathVariable("id") int id) {
    if (ingredientsService.getIngredients(id) != null);
    return ingredientsService.getIngredients(id);
  }
}
