package jsonAnalyzer;

import model.Employee;
import model.Profession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgeSalariesAnalyzer implements Analyzer {
    @Override
    public List<Analytic> toAnalytics(List<Employee> employees) {
        Map<Profession, List<Employee>> byProfession = employees.stream().collect(Collectors.groupingBy(Employee::getProfession));
        List<Analytic> result = new ArrayList<>();
        for (Map.Entry<Profession, List<Employee>> entry : byProfession.entrySet()) {
            Profession profession = entry.getKey();
            List<Employee> group = entry.getValue();
            double averageSalary = group.stream()
                    .mapToInt(Employee::getSalary).average().orElseThrow();
            int roundedAverageSalary = (int) Math.round(averageSalary);
            double averageAge = group.stream()
                    .mapToInt(Employee::getAge)
                    .average()
                    .orElseThrow();
            int roundedAverageAge = (int) Math.round(averageAge);
            result.add(new Analytic(profession, roundedAverageSalary, roundedAverageAge));
        }
        return result;
    }


}


