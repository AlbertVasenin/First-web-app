package pro.sky.recipeapp.services.impl;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.IngredientsService;

@Service
public class IngredientsServiceImpl implements IngredientsService {

  public static Integer id = 0;
  private final Map<Integer, Ingredients> listIngredients = new TreeMap<>();

  @Override
  public String getIngredients(int id) {
    if (id < 0) {
      throw new RuntimeException("ID не может быть меньше нуля!");
    } else {
      return listIngredients.get(id).getName();
    }
  }

  @Override
  public void addIngredients(String name, int count, String measure) {
    if (listIngredients.isEmpty()) {
      listIngredients.put(id++, new Ingredients(name, count, measure));
    }
  }
}
