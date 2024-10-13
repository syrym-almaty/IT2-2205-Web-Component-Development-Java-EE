package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exsertion.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(UUID.randomUUID());
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        var students = studentService.getAllStudents();

        assertEquals(1, students.size());
        assertEquals(student.getName(), students.get(0).getName());
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertEquals(student.getName(), createdStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(student.getId());

        assertEquals(student.getName(), foundStudent.getName());
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertNull(studentService.getStudentById(student.getId()));
    }

    @Test
    void testUpdateStudent() {
        Student updatedStudent = new Student();
        updatedStudent.setName("Jane Doe");
        updatedStudent.setEmail("jane.doe@example.com");

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student result = studentService.updateStudent(student.getId(), updatedStudent);

        assertEquals(updatedStudent.getName(), result.getName());
    }

    @Test
    void testUpdateStudentNotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.updateStudent(student.getId(), student);
        });
    }
}
