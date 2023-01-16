package pro.sky.recipeapp.services.impl;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.IngredientsService;

@Service
public class IngredientsServiceImpl implements IngredientsService {
  public static Integer id = 1;
  private final Map<Integer, Ingredients> listIngredients = new TreeMap<>();

  @Override
  public String getIngredients(int id) {
    if (id < 1) {
      throw new IllegalArgumentException("ID не может быть меньше нуля!");
    } else {
      return listIngredients.get(id).getTitle();
    }
  }
  @Override
  public void saveIngredients(Ingredients ingredients) {
    listIngredients.put(id++, ingredients);
  }
}
