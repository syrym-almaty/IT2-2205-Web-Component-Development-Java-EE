package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(UUID.randomUUID());
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testCreateStudent() throws Exception {
        String studentJson = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}";

        when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(student.getId())).thenReturn(student);

        mockMvc.perform(get("/api/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/" + student.getId()))
                .andExpect(status().isNoContent());

        // Вы можете добавить дополнительную проверку, что метод deleteStudent был вызван
        Mockito.verify(studentService).deleteStudent(student.getId());
    }

    @Test
    void testUpdateStudent() throws Exception {
        String studentJson = "{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}";

        when(studentService.updateStudent(Mockito.any(UUID.class), Mockito.any(Student.class))).thenReturn(student);

        mockMvc.perform(patch("/api/students/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }
}

