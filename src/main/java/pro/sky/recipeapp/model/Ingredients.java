package pro.sky.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
public class Ingredients {

  private String title;
  private Integer count;
  private String measure;

}
