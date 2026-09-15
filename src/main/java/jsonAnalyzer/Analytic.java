package jsonAnalyzer;

import model.Profession;

public class Analytic {
    private Profession profession;
    private int averageSalary;
    private int averageAge;

    public Analytic(Profession PROFESSION, int averageSalary, int averageAge) {
        this.profession = PROFESSION;
        this.averageSalary = averageSalary;
        this.averageAge = averageAge;
    }


    public Profession getPROFESSION() {
        return profession;
    }

    public void setPROFESSION(Profession PROFESSION) {
        this.profession = PROFESSION;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }

    public int getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }
}
