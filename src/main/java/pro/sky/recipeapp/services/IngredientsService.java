package pro.sky.recipeapp.services;

import java.util.Map;
import pro.sky.recipeapp.model.Ingredients;

public interface IngredientsService {

  Ingredients getIngredients(long id);

  Ingredients editIngredients(long id, Ingredients ingredients);

  long addIngredients(Ingredients ingredients);

  boolean deleteIngredient(long id);

  Map<Long, Ingredients> getAllIngredients();
}
