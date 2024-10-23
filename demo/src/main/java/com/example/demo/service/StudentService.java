package com.example.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;  // Подключаем GradeRepository для получения оценок студента

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    public Student updateStudent(UUID id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    // Add other fields as necessary
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }
    
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    // Новый метод для расчета GPA
    public Double calculateGPA(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        // Получаем все оценки студента
        List<Grade> grades = gradeRepository.findByStudent(student);

        // Если нет оценок, возвращаем 0.0
        if (grades.isEmpty()) {
            return 0.0;
        }

        // Вычисляем среднее значение всех оценок
        double totalScore = grades.stream().collect(Collectors.averagingDouble(Grade::getScore));
        
        return totalScore;
    }
}
