package pro.sky.recipeapp.services;

import java.io.File;

public interface FilesService {

  boolean saveToFile(String json, String dataFileName);

  String readeFromFile(String dataFileName);

  boolean cleanFile(String dataFileName);

  File getDataFile(String dataNameFile);
}
