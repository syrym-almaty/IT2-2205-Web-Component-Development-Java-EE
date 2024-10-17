package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String title;
    private String author;
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