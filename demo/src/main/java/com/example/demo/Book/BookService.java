package com.example.demo.Book;

import java.util.List;
import java.util.UUID;

public class BookService {
    public void deleteBook(UUID id) {
    }

    public Book updateBook(UUID id, Book bookDetails) {
        return bookDetails;
    }

    public Book getBookById(UUID id) {
        return null;
    }

    public Book createBook(Book book) {
        return book;
    }

    public List<Book> getAllBooks() {
        return List.of();
    }
}
