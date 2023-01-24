package pro.sky.recipeapp.services;

public interface FilesService {

  boolean saveToFile(String json, String dataFileName);

  String readeFromFile(String dataFileName);
}
