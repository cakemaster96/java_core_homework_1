package jsonAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employee;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Передайте 3 необходимых аргумента: путь к исходными файлами, путь для сохранения аналитики, токен для почты");
            System.exit(1);
        }
        Path inputDirectory = Paths.get(args[0]);  //Путь к директории, где лежат исходные файлы
        ObjectMapper mapper = new ObjectMapper(); //Маппер для приведения json к объектам
        Analyzer analyzer = new AgeSalariesAnalyzer(); //Создаём реализацию анализатора. Если потрубуется другой формат аналитики сможем написать ещё одну реализацию.
        EmailNotificationService emailNotificationService = new EmailNotificationService(args[2]); //Создаём экземпляр сервис нотификации
        Path processedDirectory = inputDirectory.resolve("processed"); //Поддиректория куда будут попадать обработанные файлы
        Files.createDirectories(processedDirectory);
        Path outputDirectory = Path.of(args[1]);
        Files.createDirectories(outputDirectory);
        List<Path> files = FilesUtils.findInputFiles(inputDirectory); //формируем список путей к файлам. findInputFiles валидирует файлы
        for (Path file : files) {
            try {
                String text = Files.readString(file); //читаем строку из файла
                if (text.isBlank()) {
                    System.err.println(
                            "Пропущен пустой файл: " + file.toAbsolutePath()
                    );
                    continue;
                }
                List<Employee> employees = mapper.readValue(text, new TypeReference<ArrayList<Employee>>() {});
                List<Analytic> analytics = analyzer.toAnalytics(employees);
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(analytics);
                String messageContent = "Файл " + file.getFileName()
                        .toString() + " был проанализирован  " + ZonedDateTime.now();
                String outputFileName = file.getFileName()
                        .toString()
                        .replace("_data.txt", "_analytics.txt");
                Path outputFile = outputDirectory.resolve(outputFileName);
                Files.write(outputFile, json.getBytes());
                emailNotificationService.sendEmail("deviantvector@gmail.com", messageContent, json.getBytes());
                Path destination = processedDirectory.resolve(file.getFileName());
                Files.move(file, destination); //Перемещаем прочитанный файл директорию processedDirectory
            }
            catch (IOException e) {
                System.err.println("Ошибка обработки файла: " + file.toAbsolutePath());
                e.printStackTrace();
            }
        }
    }

}

