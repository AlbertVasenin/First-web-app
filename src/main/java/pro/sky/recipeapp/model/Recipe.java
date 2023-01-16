package pro.sky.recipeapp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Recipe {

  private String name;
  private int time;
  private List<Ingredients> ingredientsList = new ArrayList<>();
  private List<String> instruction = new ArrayList<>();

  public void saveIngredient(Ingredients ingredients) {
    if (ingredientsList == null) {
      ingredientsList.add(ingredients);
    }
  }

  public void setIngredientsList(Ingredients ingredients) {
    this.ingredientsList.add(ingredients);
  }

  public String showListIngredientsInRecipeId() {
    for (Ingredients ingredients : ingredientsList) {
      return ingredients.getTitle();
    }
    return null;
  }
}
