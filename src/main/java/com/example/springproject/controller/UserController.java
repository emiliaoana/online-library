package com.example.springproject.controller;

import com.example.springproject.model.Book;
import com.example.springproject.model.BorrowHistory;
import com.example.springproject.model.User;
import com.example.springproject.service.BorrowHistoryService;
import com.example.springproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found.");
        }
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/borrow/{id}/{bookId}")
    public User borrowBook(@PathVariable Long id, @PathVariable Long bookId) {
        return userService.saveBook(id, bookId);
    }

    @PostMapping("/return/{id}/{bookId}")
    public User returnBook(@PathVariable Long id, @PathVariable Long bookId) {
        return userService.deleteBook(id, bookId);
    }

    @GetMapping("/borrowed/{id}")
    public List<Book> getAllBooksByUser(@PathVariable Long id) {
        return userService.getBookByUserId(id);
    }
}
