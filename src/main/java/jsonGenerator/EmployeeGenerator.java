package jsonGenerator;

import model.Employee;
import model.Profession;
import net.datafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class EmployeeGenerator {

    private Faker faker = new Faker(Locale.of("ru"));

    public Employee generate() {
        Profession[] professions = Profession.values();
        int index = faker.number().numberBetween(0, professions.length);

        return new Employee(UUID.randomUUID(), faker.name().fullName(), faker.number().numberBetween(18, 66), faker.number().numberBetween(50000, 150000), professions[index]
        );
    }
}



