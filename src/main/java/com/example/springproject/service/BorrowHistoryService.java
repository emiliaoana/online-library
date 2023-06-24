package com.example.springproject.service;

import com.example.springproject.model.BorrowHistory;
import com.example.springproject.repository.BorrowHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowHistoryService {
    private final BorrowHistoryRepository borrowHistoryRepository;

    public void saveBorrow(Long userId, Long bookId) {
        borrowHistoryRepository.save(BorrowHistory.builder().userId(userId).bookId(bookId).build());
    }


}
