package com.example.springproject.controller;

import com.example.springproject.model.Book;
import com.example.springproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Book> getAllAvailable() {
        return bookService.getAllAvailableBooks();
    }

    @GetMapping("/available/{title}")
    public Book isAvailable(@PathVariable String title) {
        return bookService.isAvailable(title);
    }

    @PostMapping("{id}/borrow")
    public ResponseEntity<Object> borrowBook(@PathVariable Long id) {
        try {
            Book borrowedBook = bookService.borrowBook(id);
            if (!borrowedBook.isAvailable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not available.");
            } else {
                return ResponseEntity.ok(borrowedBook);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }


    @PostMapping("{id}/return")
    public ResponseEntity<Object> returnBook(@PathVariable Long id) {
        try {
            Book returnedBook = bookService.returnBook(id);
            if (returnedBook.isAvailable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You already returned this book.");
            } else {
                return ResponseEntity.ok(returnedBook);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }

    @GetMapping("/author/{author}")
    public List<Book> getAuthor(@PathVariable String author) {
        return bookService.getAuthor(author);
    }
//VERIFICAT
//    @GetMapping("/author")
//    public List<Book> getAllBooksByAuthor(@RequestParam("author") String author) {
//        return bookService.getAuthor(author);
//    }

    @GetMapping("/sort/{author}")
    public List<Book> getAuthorSorted(@PathVariable String author) {
        return bookService.getAuthorSorted(author);
    }

}
