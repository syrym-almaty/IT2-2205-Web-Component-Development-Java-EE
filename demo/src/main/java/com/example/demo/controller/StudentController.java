package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@Validated // Enable validation
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get All Students", description = "Retrieve a list of all students")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Create Student", description = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public Student createStudent(
            @Parameter(description = "Student object to be created", required = true)
            @Valid @RequestBody Student student) { // Use @Valid here
        return studentService.createStudent(student);
    }

    @Operation(summary = "Get Student by ID", description = "Retrieve a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public Student getStudentById(
            @Parameter(description = "UUID of the student to retrieve", required = true)
            @PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Delete Student", description = "Delete a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @Parameter(description = "UUID of the student to delete", required = true)
            @PathVariable UUID id) {
        studentService.deleteStudent(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudentPartial(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> updates) {
        Student updatedStudent = studentService.updateStudentPartially(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }
}