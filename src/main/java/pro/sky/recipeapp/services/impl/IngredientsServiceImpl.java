package pro.sky.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.IngredientsService;

@Service
public class IngredientsServiceImpl implements IngredientsService {

  @Value("${name.of.ingredients.data.file}")
  private String dataFileName;
  private FilesService filesService;

  public static long id = 1;
  private Map<Long, Ingredients> listIngredients = new TreeMap<>();

  public IngredientsServiceImpl(FilesService filesService) {
    this.filesService = filesService;
  }

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
      saveToFile();
      return ingredients;
    }
    return null;
  }

  @Override
  public long addIngredients(Ingredients ingredients) {
    listIngredients.getOrDefault(id, null);
    listIngredients.put(id, ingredients);
    saveToFile();
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

  private void saveToFile() {
    try {
      String json = new ObjectMapper().writeValueAsString(listIngredients);
      filesService.saveToFile(json, dataFileName);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }

  private void readFromFile() {
    String json = filesService.readeFromFile(dataFileName);
    try {
      listIngredients = new ObjectMapper().readValue(json,
          new TypeReference<TreeMap<Long, Ingredients>>() {
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  @PostConstruct
  private void init(){
    readFromFile();
  }
}


