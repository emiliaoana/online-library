package com.example.springproject.repository;

import com.example.springproject.model.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory,Long> {
}
