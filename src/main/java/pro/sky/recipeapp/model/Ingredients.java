package pro.sky.recipeapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Ingredients {

  private String title;
  private Integer count;
  private String measure;

}
