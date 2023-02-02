package pro.sky.recipeapp.model;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

  @NotBlank
  private String name;
  @Positive
  private int time;
  @NotEmpty
  private List<Ingredients> ingredientsList;
  private List<String> instruction;

  @Override
  public String toString() {
    return name + " " +
        " " + time +
        " " + ingredientsList +
        " " + instruction;
  }
}
