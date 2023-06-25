package com.example.springproject.repository;

import com.example.springproject.model.Book;
import com.example.springproject.model.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
    @Query("select b from Book b where b.id in (select bookId from BorrowHistory group by bookId order by count(bookId) desc)")
    List<Book> getMostBorrowedBooks();
}
