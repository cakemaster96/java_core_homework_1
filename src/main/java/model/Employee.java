package model;

import java.util.UUID;

public class Employee {

    private UUID uuid;
    private String name;
    private int age;
    private int salary;
    private Profession profession;

    public Employee() {

    }

    public Employee(UUID uuid, String name, int age, int salary, Profession profession) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.profession = profession;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}

