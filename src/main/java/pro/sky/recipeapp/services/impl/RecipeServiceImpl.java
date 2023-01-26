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
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

  @Value("${name.of.recipes.data.file}")
  private String dataFileName;

  private FilesService filesService;
  public static long id = 1;
  private Map<Long, Recipe> listRecipes = new TreeMap<>();

  public RecipeServiceImpl(FilesService filesService) {
    this.filesService = filesService;
  }

  @Override
  public Recipe getRecipe(long id) {
    for (Entry<Long, Recipe> entry : listRecipes.entrySet()) {
      if (entry != null && entry.getKey() == id) {
        Recipe recipe = listRecipes.get(id);
        if (recipe != null) {
          return recipe;
        }
      }
    }
    return null;
  }

  @Override
  public long addRecipe(Recipe recipe) {
    listRecipes.getOrDefault(id, null);
    listRecipes.put(id, recipe);
    saveToFile();
    return id++;
  }

  @Override
  public Recipe editRecipe(long id, Recipe recipe) {
    if (listRecipes.containsKey(id)) {
      listRecipes.put(id, recipe);
      saveToFile();
      return recipe;
    }
    return null;
  }

  @Override
  public boolean deleteRecipe(long id) {
    if (listRecipes.containsKey(id)) {
      listRecipes.remove(id);
      return true;
    }
    return false;
  }

  @Override
  public Map<Long, Recipe> getAllRecipes() {
    return listRecipes;
  }

  private void saveToFile() {
    try {
      String json = new ObjectMapper().writeValueAsString(listRecipes);
      filesService.saveToFile(json, dataFileName);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }

  private void readFromFile() {
    String json = filesService.readeFromFile(dataFileName);
    try {
      listRecipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @PostConstruct
  private void init() {
    readFromFile();
  }
}
