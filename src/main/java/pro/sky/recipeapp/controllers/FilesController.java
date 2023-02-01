package pro.sky.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeapp.services.FilesService;

@Tag(name = "Файловый контроллер")
@RestController
@RequestMapping("/files")
public class FilesController {

  private final FilesService filesService;

  public FilesController(FilesService filesService) {
    this.filesService = filesService;
  }

  @Operation(summary = "Скачать файлы", description = "Чтобы скачать файл, скопируйте "
      + "содержимое из кавычек: \"ingredients\" или \"recipes\" и вставьте в параметр dataNameFile")
  @GetMapping(value = "/export/{dataNameFile}")
  public ResponseEntity<InputStreamResource> downLoadFiles(@PathVariable String dataNameFile)
      throws FileNotFoundException {
    File file = filesService.getDataFile(dataNameFile);
    if (file.exists()) {
      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
          .contentLength(file.length())
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + dataNameFile + ".json")
          .body(resource);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(summary = "Загрузить файлы", description =
      "Чтобы заменить файл ингредиентов или рецептов, скопируйте "
          + "необходимое название файла из кавычек: \"ingredients\" или \"recipes\" и вставьте в параметр dataNameFile, затем выберите соответсвующий файл")
  @PostMapping(value = "/import/{dataNameFile}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> upLoadFiles(@PathVariable String dataNameFile,
      @RequestParam MultipartFile file) {
    filesService.cleanFile(dataNameFile);
    File dataFile = filesService.getDataFile(dataNameFile);
    try (FileOutputStream fos = new FileOutputStream(dataFile)) {
      IOUtils.copy(file.getInputStream(), fos);
      return ResponseEntity.ok().build();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
