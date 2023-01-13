package pro.sky.recipeapp.services;

import java.util.List;
import pro.sky.recipeapp.model.Ingredients;

public interface RecipeService {

  String getRecipe(int id);

  String addRecipe(String name, int time, List<String> ingredients, List<String> instruction);
}
