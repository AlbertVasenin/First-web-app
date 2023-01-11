package pro.sky.recipeapp.services.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

  public static Integer id = 0;
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
  public void addRecipe(String name, int time, List<Ingredients> ingredients,
      List<String> instruction) {
    if (listRecipes.isEmpty()) {
      listRecipes.put(id++, new Recipe(name, time, ingredients, instruction));
    }
  }
}
