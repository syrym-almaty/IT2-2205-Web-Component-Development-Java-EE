package com.example.demo.Book;


import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    void deleteById(UUID id);
}
