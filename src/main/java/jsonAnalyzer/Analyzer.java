package jsonAnalyzer;

import model.Employee;

import java.util.List;

public interface Analyzer {
    public abstract List<Analytic> toAnalytics(List<Employee> employees); //Метод который возвращает аналитику по сотрудникам
}
