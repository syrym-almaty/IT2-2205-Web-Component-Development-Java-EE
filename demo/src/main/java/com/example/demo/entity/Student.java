package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Student {

    @Id
    private UUID id;
    private String name;
    private int age;

    // Конструкторы, геттеры и сеттеры

    public Student() {
        this.id = UUID.randomUUID(); // Генерация нового UUID при создании студента
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
