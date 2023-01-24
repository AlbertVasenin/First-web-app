package pro.sky.recipeapp.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeapp.services.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

  @Value("${path.to.data.file}")
  private String dataFilePath;

  @Override
  public boolean saveToFile(String json, String dataFileName) {
    Path path = Path.of(dataFilePath, dataFileName);
    try {
      cleanFile(dataFileName);
      Files.writeString(path, json);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public String readeFromFile(String dataFileName) {
    Path path = Path.of(dataFilePath, dataFileName);
    try {
      return Files.readString(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean cleanFile(String dataFileName) {
    try {
      Path path = Path.of(dataFilePath,dataFileName);
      Files.deleteIfExists(path);
      Files.createFile(path);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
