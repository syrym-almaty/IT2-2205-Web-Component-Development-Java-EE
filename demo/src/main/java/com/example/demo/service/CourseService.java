package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.entity.Course;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void enrollStudentInCourse(UUID studentId, UUID courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (student.getCourses().size() >= 5) {
            throw new BusinessException("Student cannot enroll in more than 5 courses");
        }

        if (course.getStudents().size() >= 30) {
            throw new BusinessException("Course cannot have more than 30 students");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }
}