package pro.sky.recipeapp.services.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.IngredientsService;

@Service
public class IngredientsServiceImpl implements IngredientsService {

  public static long id = 1;
  private final Map<Long, Ingredients> listIngredients = new TreeMap<>();

  @Override
  public Ingredients getIngredients(long id) {
    for (Entry<Long, Ingredients> entry : listIngredients.entrySet()) {
      if (entry != null && entry.getKey() == id) {
        Ingredients ingredients = listIngredients.get(id);
        if (ingredients != null) {
          return ingredients;
        }
      }
    }
    return null;
  }

  @Override
  public Ingredients editIngredients(long id, Ingredients ingredients) {
    if (listIngredients.containsKey(id)) {
      listIngredients.put(id, ingredients);
      return ingredients;
    }
    return null;
  }

  @Override
  public long addIngredients(Ingredients ingredients) {
    listIngredients.getOrDefault(id, null);
    listIngredients.put(id, ingredients);
    return id++;
  }


  @Override
  public boolean deleteIngredient(long id) {
    if (listIngredients.containsKey(id)) {
      listIngredients.remove(id);
      return true;
    }
    return false;
  }

  public Map<Long, Ingredients> getAllIngredients() {
    for (long i = 0; i < listIngredients.size(); ) {
      listIngredients.get(++i);
    }
    return listIngredients;
  }
}


