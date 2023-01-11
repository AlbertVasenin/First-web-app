package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping
  public String startApp() {
    return "Приложение запущено";
  }

  @GetMapping("/info")
  public String infoAboutApp() {
    return "Разработчик: Альберт Васенин, "
        + "приложение \"RecipeApp\", "
        + "дата создания: 28.12.2022 год, "
        + "приложение показывает содержание выбранного рецепта из списка";
  }
}
