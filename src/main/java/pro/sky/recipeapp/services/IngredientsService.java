package pro.sky.recipeapp.services;

import pro.sky.recipeapp.model.Ingredients;

public interface IngredientsService {
  String getIngredients(int id);

  void saveIngredients(Ingredients ingredients);

}
