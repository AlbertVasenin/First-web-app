package pro.sky.recipeapp.model;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class Ingredients {

  private String title;
  private Integer count;
  private String measure;

  public Ingredients(String title, int count, String measure) {
    setTitle(title);
    setCount(count);
    setMeasure(measure);
  }

  public String setTitle(String title) {
    if (title != null && !title.isEmpty()) {
      this.title = title;
    } else {
      throw new IllegalArgumentException("Name not valid");
    }
    return title;
  }

  public Integer setCount(Integer count) {
    if (count != null && count >= 0) {
      this.count = count;
    } else {
      throw new IllegalArgumentException("Count not be < 0");
    }
    return count;
  }

  public String setMeasure(String measure) {
    if (measure != null && !measure.isEmpty()) {
      this.measure = measure;
    } else {
      throw new IllegalArgumentException("Input measure!");
    }
    return measure;
  }
}
