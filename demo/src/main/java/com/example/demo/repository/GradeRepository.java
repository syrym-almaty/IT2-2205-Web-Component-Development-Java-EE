package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Grade;
import com.example.demo.entity.GradeId;
import com.example.demo.entity.Student;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    // Метод для поиска всех оценок студента
    List<Grade> findByStudent(Student student);
}