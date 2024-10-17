package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @Pattern(regexp = "\\d{13}", message = "ISBN must be 13 digits")
    private String isbn;

    private boolean available;

    // Constructors, Getters, and Setters
    public Object getTitle() {
        return title;
    }

    public Object getAuthor() {
        return author;
    }

    public Object getIsbn() {
        return isbn;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setTitle(Object title) {
        this.title = title.toString();
    }

    public void setAuthor(Object author) {
        this.author = author.toString();
    }

    public void setIsbn(Object isbn) {
        this.isbn = isbn.toString();
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}