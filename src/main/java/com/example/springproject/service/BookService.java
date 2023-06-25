package com.example.springproject.service;

import com.example.springproject.model.Book;
import com.example.springproject.model.User;
import com.example.springproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllAvailableBooks() {
        return bookRepository.getAllAvailableBooks();
    }

    public Book isAvailable(String title) {
        return bookRepository.getAvailableByTitle(title);
    }

    public Book borrowBook(Long id) {
        if (bookRepository.findById(id).orElseThrow().isAvailable()) {
            Book book = bookRepository.findById(id).orElseThrow();
            book.setAvailable(false);
            bookRepository.save(book);
        }
        return bookRepository.findById(id).orElseThrow();
    }

    public void updateUser(Long id, Long userId) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setUser(User.builder().id(userId).build());

    }

    public Book returnBook(Long id) {
        if (!bookRepository.findById(id).orElseThrow().isAvailable()) {
            Book book = bookRepository.findById(id).orElseThrow();
            book.setAvailable(true);
            bookRepository.save(book);
        }
        return bookRepository.findById(id).orElseThrow();
    }

    public List<Book> getAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getAuthorSorted(String author) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, author));
    }

    public Page<Book> getBookPagination(int offset, int pageSize) {
        return bookRepository.findAll(PageRequest.of(offset, pageSize));

    }

    public Page<Book> getBookPaginationAndSorting(int offset, int pageSize, String author) {
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(author)));
        return bookPage;
    }
}
