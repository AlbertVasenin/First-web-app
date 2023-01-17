package pro.sky.recipeapp.services;

import java.util.Map;
import pro.sky.recipeapp.model.Recipe;

public interface RecipeService {

  Recipe getRecipe(long id);

  long addRecipe(Recipe recipe);

  Recipe editRecipe(long id, Recipe recipe);

  boolean deleteRecipe(long id);

  Map<Long, Recipe> getAllRecipes();
}
