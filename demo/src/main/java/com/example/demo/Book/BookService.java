package com.example.demo.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Получение списка всех книг
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Создание новой книги
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Получение книги по ID
    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Обновление существующей книги
    public Book updateBook(UUID id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setAvailable(bookDetails.isAvailable());
            return bookRepository.save(book);
        }
        return null; // Или выбросить исключение, если книга не найдена
    }

    // Удаление книги
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    // Поиск книг по названию или автору
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}