package pro.sky.recipeapp.services;

import java.util.List;
import pro.sky.recipeapp.model.Ingredients;

public interface RecipeService {

  String getRecipe(int id);

  void addRecipe(String name, int time, List<Ingredients> ingredients, List<String> instruction);
}
