//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private UUID testBookId;
//
//    @BeforeEach
//    public void setUp() {
//        // Устанавливаем тестовый ID книги для использования в тестах
//        testBookId = UUID.randomUUID();
//    }
//
//    @Test
//    public void testCreateBook() throws Exception {
//        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
//        String bookJson = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(post("/api/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(bookJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Effective Java"));
//    }
//
//    @Test
//    public void testGetAllBooks() throws Exception {
//        mockMvc.perform(get("/api/books"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testGetBookById() throws Exception {
//        mockMvc.perform(get("/api/books/{id}", testBookId))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testUpdateBook() throws Exception {
//
//        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
//        String bookJson = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(post("/api/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bookJson));
//
//        book.setTitle("Effective Java (Updated)");
//        String updatedBookJson = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(put("/api/books/{id}", testBookId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updatedBookJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Effective Java (Updated)"));
//    }
//
//    @Test
//    public void testDeleteBook() throws Exception {
//
//        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
//        String bookJson = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(post("/api/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bookJson));
//
//        mockMvc.perform(delete("/api/books/{id}", testBookId))
//                .andExpect(status().isNoContent());
//
//        mockMvc.perform(get("/api/books/{id}", testBookId))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testSearchBooks() throws Exception {
//
//        Book book = new Book(testBookId, "Effective Java", "Joshua Bloch", "9780134685991", true);
//        String bookJson = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(post("/api/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(bookJson));
//
//        mockMvc.perform(get("/api/books/search?keyword=Effective"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("Effective Java"));
//    }
//}