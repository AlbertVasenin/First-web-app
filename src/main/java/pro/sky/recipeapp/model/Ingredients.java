package pro.sky.recipeapp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

  @NotBlank
  private String title;
  @Positive
  private Integer count;
  @NotBlank
  private String measure;

}
