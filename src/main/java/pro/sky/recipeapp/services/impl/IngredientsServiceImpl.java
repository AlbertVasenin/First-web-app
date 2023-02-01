package pro.sky.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.exeptions.ExceptionsApp;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.IngredientsService;

@Service
public class IngredientsServiceImpl implements IngredientsService {

  @Value("${name.of.ingredients.data.file}")
  private String dataFileName;
  private final FilesService filesService;

  public static long id = 1;
  private Map<Long, Ingredients> listIngredients = new TreeMap<>();

  public IngredientsServiceImpl(FilesService filesService) {
    this.filesService = filesService;
  }

  @Override
  public Ingredients getIngredients(long id) throws ExceptionsApp {
    if (listIngredients.containsKey(id)) {
      return listIngredients.get(id);
    } else {
      throw new ExceptionsApp("Ингредиента с таким id не существует");
    }
  }

  @Override
  public Ingredients editIngredients(long id, Ingredients ingredients) throws ExceptionsApp {
    if (listIngredients.containsKey(id)) {
      listIngredients.put(id, ingredients);
      saveToFile();
      return ingredients;
    } else {
      throw new ExceptionsApp("Ингредиент не найден");
    }
  }

  @Override
  public long addIngredients(Ingredients ingredients) throws ExceptionsApp {
    if (!listIngredients.containsValue(ingredients)) {
      listIngredients.put(id, ingredients);
      saveToFile();
      return id++;
    } else {
      throw new ExceptionsApp("Такой ингредиент есть в списке");
    }
  }


  @Override
  public boolean deleteIngredient(long id) throws ExceptionsApp {
    if (listIngredients.containsKey(id)) {
      listIngredients.remove(id);
      return true;
    } else {
      throw new ExceptionsApp("Ингредиент не найден");
    }
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
      throw new RuntimeException("Ошибка сохранения файла");
    }

  }

  private void readFromFile() {
    String json = filesService.readeFromFile(dataFileName);
    try {
      listIngredients = new ObjectMapper().readValue(json,
          new TypeReference<TreeMap<Long, Ingredients>>() {
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Ошибка чтения файла");
    }
  }

  @PostConstruct
  private void init() {
    readFromFile();
  }
}


