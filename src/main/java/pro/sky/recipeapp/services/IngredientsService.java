package pro.sky.recipeapp.services;

public interface IngredientsService {
  String getIngredients(int id);

  void addIngredients(String name,int count, String measure);
}
