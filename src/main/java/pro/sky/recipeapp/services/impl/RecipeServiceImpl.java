package pro.sky.recipeapp.services.impl;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
  public static Integer id = 1;
  private final Map<Integer, Recipe> listRecipes = new TreeMap<>();

  @Override
  public String getRecipe(int id) {
    if (id < 0) {
      throw new RuntimeException("ID не может быть меньше нуля!");
    } else {
      return listRecipes.get(id).getName();
    }
  }
  @Override
  public void saveRecipe(Recipe recipe) {
    listRecipes.put(id++, recipe);
  }
}
