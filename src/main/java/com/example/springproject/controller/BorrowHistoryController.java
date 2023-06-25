package com.example.springproject.controller;

import com.example.springproject.model.Book;
import com.example.springproject.service.BorrowHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("borrowHistory")
public class BorrowHistoryController {
    private final BorrowHistoryService borrowHistoryService;

    @GetMapping
    public List<Book> getMostBorrowedBooks() {
        return borrowHistoryService.getMostBorrowedBooks();
    }
}
