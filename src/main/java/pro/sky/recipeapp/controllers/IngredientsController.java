package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/save")
  public String saveIngredients(@RequestParam (value = "title") String title,
      @RequestParam (value = "count") Integer count,
      @RequestParam (value = "measure") String measure) {
    Ingredients ingredients = new Ingredients(title,count,measure);
    ingredientsService.saveIngredients(ingredients);
    return "redirect:/ingredients";
  }

  @GetMapping("/{id}")
  public String getIngredients(@PathVariable(value = "id", required = false) int id) {
    if (ingredientsService.getIngredients(id) != null);
    return ingredientsService.getIngredients(id);
  }
}
