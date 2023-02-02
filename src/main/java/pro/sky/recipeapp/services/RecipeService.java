package pro.sky.recipeapp.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import pro.sky.recipeapp.exeptions.ExceptionsApp;
import pro.sky.recipeapp.model.Recipe;

public interface RecipeService {

  Recipe getRecipe(long id) throws ExceptionsApp;

  long addRecipe(Recipe recipe) throws ExceptionsApp;

  Recipe editRecipe(long id, Recipe recipe) throws ExceptionsApp;

  boolean deleteRecipe(long id) throws ExceptionsApp;

  Map<Long, Recipe> getAllRecipes();

  Path createRecipesInTxt() throws IOException;
}
