package pro.sky.recipeapp.services;

import pro.sky.recipeapp.model.Recipe;

public interface RecipeService {

  String getRecipe(int id);

  void saveRecipe(Recipe recipe);
}
