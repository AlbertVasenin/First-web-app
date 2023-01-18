package pro.sky.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Статус", description = "GET запросы, показывающие статус приложения и инфо о разработчике")
public class Controller {

  @Operation(
      summary = "Статус приложения",
      description = "Получение информации об успешном запуске приложения")
  @GetMapping
  public String startApp() {
    return "Приложение запущено";
  }

  @Operation(
      summary = "Информация",
      description = "Получение информации о разработчике")
  @GetMapping("/info")
  public String infoAboutApp() {
    return "Разработчик: Альберт Васенин, "
        + "приложение \"RecipeApp\", "
        + "дата создания: 28.12.2022 год, "
        + "приложение показывает содержание выбранного рецепта из списка";
  }
}
