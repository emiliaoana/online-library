package com.example.springproject;

import com.example.springproject.model.Book;
import com.example.springproject.repository.BookRepository;
import com.example.springproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGetBookById() {
        //Given
        Long bookId = 1L;
        Book book = Book.builder().id(bookId).title("Test Book").build();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        //When
        Book result = bookService.getById(bookId);
        //Then
        assert result.getId().equals(bookId);
    }

    @Test
    public void testGetBookById2() {
        //Given
        Long bookId = 2L;
        Book book = Book.builder().id(bookId).title("Test Book 2").build();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        //When
        Book result = bookService.getById(bookId);
        //Then
        assert result.getId().equals(bookId);
    }

    @Test
    public void testSaveBook() {
        //Given
        Long bookId = 1L;
        Book book = Book.builder().id(bookId).title("Save Book").build();
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book result = bookService.saveBook(book);
        //Then
        assert book.equals(result);
    }

    @Test
    public void testSaveBook2() {
        //Given
        Long bookId = 2L;
        Book book = Book.builder().id(bookId).title("Save Book 2").build();
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book result = bookService.saveBook(book);
        //Then
        assert book.equals(result);
    }
}
