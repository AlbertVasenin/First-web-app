package pro.sky.recipeapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe {

  private String name;
  private int time;
  private List<Ingredients> ingredients;
  private List<String> instruction;

}
