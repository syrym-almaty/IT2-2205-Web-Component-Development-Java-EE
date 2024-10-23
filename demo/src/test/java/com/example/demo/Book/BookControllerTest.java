package com.example.demo.Book;

import com.example.demo.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID testBookId;

    @BeforeEach
    public void setUp() {
        // Устанавливаем тестовый ID книги для использования в тестах
        testBookId = UUID.randomUUID();
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetBookById() throws Exception {
        // Здесь нужно будет сначала создать книгу, а затем попробовать её получить
        mockMvc.perform(get("/api/books/{id}", testBookId))
                .andExpect(status().isNotFound()); // Предполагаем, что книги с этим ID ещё нет
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Создаем книгу для обновления
        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson));

        // Теперь обновим книгу
        book.setTitle("Effective Java (Updated)");
        String updatedBookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(put("/api/books/{id}", testBookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java (Updated)"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Создаем книгу для удаления
        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson));

        // Теперь удаляем книгу
        mockMvc.perform(delete("/api/books/{id}", testBookId))
                .andExpect(status().isNoContent());

        // Проверяем, что книга больше не существует
        mockMvc.perform(get("/api/books/{id}", testBookId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchBooks() throws Exception {
        // Создаем книгу для поиска
        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson));

        // Выполняем поиск
        mockMvc.perform(get("/api/books/search?keyword=Effective"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Effective Java"));
    }
}

