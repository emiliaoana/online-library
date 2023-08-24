package com.example.springproject.repository;

import com.example.springproject.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.isAvailable = true")
    List<Book> getAllAvailableBooks();

    @Query("select b from Book b where b.isAvailable = true and b.title = ?1 ")
    Book getAvailableByTitle(String title);

    Optional<Book> findById(Long id);

    List<Book> findByAuthor(String author);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.deleted = true WHERE b.id = ?1 and b.deleted = false")
    void softDeleteById(Long id);
}
