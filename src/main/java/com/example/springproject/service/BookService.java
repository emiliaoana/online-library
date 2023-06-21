package com.example.springproject.service;

import com.example.springproject.model.Book;
import com.example.springproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public Book getById(Long id){
        return bookRepository.findById(id).orElseThrow();
    }
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAllAvailableBooks(){
        return bookRepository.getAllAvailableBooks();
    }
    public Book isAvailable(String title){
        return bookRepository.getAvailableByTitle(title);
    }
    public Book borrowBook(Long id){
        if (bookRepository.findById(id).orElseThrow().isAvailable()) {
            Book book = bookRepository.findById(id).orElseThrow();
            book.setAvailable(false);
            bookRepository.save(book);
        }
        return bookRepository.findById(id).orElseThrow();
    }
    public Book returnBook(Long id){
        if(!bookRepository.findById(id).orElseThrow().isAvailable()){
            Book book = bookRepository.findById(id).orElseThrow();
            book.setAvailable(true);
            bookRepository.save(book);
        }
        return bookRepository.findById(id).orElseThrow();
    }
    public List<Book> getAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

}
