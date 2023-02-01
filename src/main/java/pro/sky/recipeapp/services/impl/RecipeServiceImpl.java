package pro.sky.recipeapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.exeptions.ExceptionsApp;
import pro.sky.recipeapp.model.Ingredients;
import pro.sky.recipeapp.model.Recipe;
import pro.sky.recipeapp.services.FilesService;
import pro.sky.recipeapp.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

  @Value("${name.of.recipes.data.file}")
  private String dataFileName;

  private final FilesService filesService;
  public static long id = 1;
  private Map<Long, Recipe> listRecipes = new TreeMap<>();

  public RecipeServiceImpl(FilesService filesService) {
    this.filesService = filesService;
  }

  @Override
  public Recipe getRecipe(long id) throws ExceptionsApp {
    if (listRecipes.containsKey(id)) {
      return listRecipes.get(id);
    } else {
      throw new ExceptionsApp("Рецепта с таким id не существует");
    }
  }

  @Override
  public long addRecipe(Recipe recipe) throws ExceptionsApp {
    if (!listRecipes.containsValue(recipe)) {
      listRecipes.put(id, recipe);
      saveToFile();
      return id++;
    } else {
      throw new ExceptionsApp("Такой рецепт есть в списке");
    }
  }

  @Override
  public Recipe editRecipe(long id, Recipe recipe) throws ExceptionsApp {
    if (listRecipes.containsKey(id)) {
      listRecipes.put(id, recipe);
      saveToFile();
      return recipe;
    } else {
      throw new ExceptionsApp("Рецепт не найден");
    }
  }


  @Override
  public boolean deleteRecipe(long id) throws ExceptionsApp {
    if (listRecipes.containsKey(id)) {
      listRecipes.remove(id);
      return true;
    } else {
      throw new ExceptionsApp("Рецепт не найден");
    }
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
      throw new RuntimeException("Ошибка сохранения файла");
    }

  }

  private void readFromFile() {
    String json = filesService.readeFromFile(dataFileName);
    try {
      listRecipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Long, Recipe>>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Ошибка чтения файла");
    }
  }

  @PostConstruct
  private void init() {
    readFromFile();
  }

  @Override
  public Path createRecipesInTxt() throws IOException {
    listRecipes.getOrDefault(id, null);
    Path recipesText = filesService.createTempFile("Recipes_text");
    try (Writer writer = Files.newBufferedWriter(recipesText, StandardCharsets.UTF_8)) {
      for (Recipe recipes : listRecipes.values()) {
        StringBuilder ingredients = new StringBuilder();
        StringBuilder instructions = new StringBuilder();
        for (Ingredients ingredient : recipes.getIngredientsList()) {
          ingredients.append(ingredient).append(",\n");
        }
        for (String instr : recipes.getInstruction()) {
          instructions.append("\n").append(instr);
        }
        writer.append(recipes.getName()).append("\n").append("Время приготовления: ")
            .append(String.valueOf(recipes.getTime())).append(" минут").append("\n")
            .append("Необходимые ингредиенты:\n")
            .append(ingredients.toString()).append(" Инструкция: ")
            .append(instructions.toString());
        writer.append("\n\n");
      }
    }
    return recipesText;
  }
}




