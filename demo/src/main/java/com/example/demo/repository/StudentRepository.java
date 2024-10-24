package com.example.demo.repository;

import com.example.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @PersistenceContext
    EntityManager entityManager = null;

    public default Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public default Object save(Student student) {
        entityManager.persist(student);
        return null;
    }

}
