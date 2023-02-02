package pro.sky.recipeapp.services;

import java.util.Map;
import pro.sky.recipeapp.exeptions.ExceptionsApp;
import pro.sky.recipeapp.model.Ingredients;

public interface IngredientsService {

  Ingredients getIngredients(long id) throws ExceptionsApp;

  Ingredients editIngredients(long id, Ingredients ingredients) throws ExceptionsApp;

  long addIngredients(Ingredients ingredients) throws ExceptionsApp;

  boolean deleteIngredient(long id) throws ExceptionsApp;

  Map<Long, Ingredients> getAllIngredients();
}
