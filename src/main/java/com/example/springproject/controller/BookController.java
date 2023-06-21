package com.example.springproject.controller;

import com.example.springproject.model.Book;
import com.example.springproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/available")
    public List<Book> getAllAvailable(){
        return bookService.getAllAvailableBooks();
    }
    @GetMapping("/available/{title}")
    public Book isAvailable(@PathVariable String title){
        return bookService.isAvailable(title);
    }
    @PostMapping("{id}/borrow")
    public Book borrowBook(@PathVariable Long id){
        return bookService.borrowBook(id);
    }
    @PostMapping("{id}/return")
    public Book returnBook(@PathVariable Long id){
       return bookService.returnBook(id);
    }
}
