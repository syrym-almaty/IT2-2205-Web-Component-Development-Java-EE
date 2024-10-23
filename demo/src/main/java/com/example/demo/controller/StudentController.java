package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository; // Предполагается, что у вас есть репозиторий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(UUID id, Student updatedStudent) {
        // Сначала проверяем, существует ли студент
        Student existingStudent = getStudentById(id);

        // Обновляем поля студента
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());

        return studentRepository.save(existingStudent);
    }
}
