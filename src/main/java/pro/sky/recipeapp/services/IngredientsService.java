package pro.sky.recipeapp.services;

public interface IngredientsService {
  String getIngredients(int id);

  void addIngredients(String title,int count, String measure);
}
