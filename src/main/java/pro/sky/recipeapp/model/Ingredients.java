package pro.sky.recipeapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import pro.sky.recipeapp.services.IngredientsService;

@Data
@AllArgsConstructor
public class Ingredients {

  private String name;
  private int count;
  private String measure;
}
