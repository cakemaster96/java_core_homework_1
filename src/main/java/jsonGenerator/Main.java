package jsonGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employee;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        if (args.length != 1) {
            System.err.println("Передайте необходимый аргумент: путь для сгенерированных файлов");
            System.exit(1);
        }
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();
        EmployeeGenerator employeeGenerator = new EmployeeGenerator();
        Path outputDirectory = Paths.get(args[0]);
        while (true) {
            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < random.nextInt(91) + 10; i++) {
                Employee emp = employeeGenerator.generate(); //Создаем нового сотрудника
                employees.add(emp); //Добавляем сотрудников в список
            }
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
            String baseName = System.currentTimeMillis() + "_data";
            Path temporaryFile = outputDirectory.resolve(baseName + ".tmp");
            Path file = outputDirectory.resolve(baseName + ".txt");
            try {
                Files.write(temporaryFile, json.getBytes()); //Сначала сохраняем файл как временный, чтобы анализатор не прочитал незавершенный файл
                Files.move(temporaryFile,file,StandardCopyOption.ATOMIC_MOVE);
            } catch (IOException e) {
                e.getStackTrace();
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
