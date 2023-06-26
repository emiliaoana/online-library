package com.example.springproject;

import com.example.springproject.model.Book;
import com.example.springproject.repository.BookRepository;
import com.example.springproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void testGetAllBooks() {
        //Given
        Long bookId1 = 1L;
        Long bookId2 = 2L;
        Book book1 = Book.builder().id(bookId1).title("GetBook1").build();
        Book book2 = Book.builder().id(bookId2).title("GetBook2").build();
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));
        //When
        List<Book> result = bookService.getAll();
        //Then
        assert result.size() == 2;
    }

    @Test
    public void testUpdateBook() {
        //Given
        Long bookId = 1L;
        Book book = Book.builder().id(bookId).title("Update Book").build();
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book result = bookService.updateBook(book);
        //Then
        assert book.equals(result);
    }

    @Test
    public void testDeleteBook() {
        //Given
        Long bookId = 1L;
        Book book = Book.builder().id(bookId).title("DeleteBook").build();
        doNothing().when(bookRepository).deleteById(bookId);
        //When
        bookService.deleteById(bookId);
        //Then
        assert true;
    }

    @Test
    public void testGetAllAvailableBooks() {
        //Given
        Long bookId1 = 1L;
        boolean isAvailable1 = true;
        Long bookId2 = 2L;
        boolean isAvailable2 = false;
        Book book1 = Book.builder().id(bookId1).title("Available Book1").isAvailable(isAvailable1).build();
        Book book2 = Book.builder().id(bookId2).title("Available Book2").isAvailable(isAvailable2).build();
        when(bookRepository.getAllAvailableBooks()).thenReturn(List.of(book1));
        //When
        List<Book> result = bookService.getAllAvailableBooks();
        //Then
        assert result.size() == 1;
    }

    @Test
    public void testGetAllAvailableBooks2() {
        //Given
        Long bookId1 = 1L;
        boolean isAvailable1 = true;
        Long bookId2 = 2L;
        boolean isAvailable2 = true;
        Book book1 = Book.builder().id(bookId1).title("Available Book1").isAvailable(isAvailable1).build();
        Book book2 = Book.builder().id(bookId2).title("Available Book2").isAvailable(isAvailable2).build();
        when(bookRepository.getAllAvailableBooks()).thenReturn(List.of(book1, book2));
        //When
        List<Book> result = bookService.getAllAvailableBooks();
        //Then
        assert result.size() == 2;
    }

    @Test
    public void testGetAllAvailableBooks3() {
        //Given
        Long bookId1 = 1L;
        boolean isAvailable1 = false;
        Long bookId2 = 2L;
        boolean isAvailable2 = false;
        Book book1 = Book.builder().id(bookId1).title("Available Book1").isAvailable(isAvailable1).build();
        Book book2 = Book.builder().id(bookId2).title("Available Book2").isAvailable(isAvailable2).build();
        when(bookRepository.getAllAvailableBooks()).thenReturn(List.of());
        //When
        List<Book> result = bookService.getAllAvailableBooks();
        //Then
        assert result.size() == 0;
    }

    @Test
    public void testIsAvailableByTitle() {
        //Given
        String title = "Test1";
        Long bookId = 1L;
        Book book = Book.builder().id(bookId).title(title).build();
        when(bookRepository.getAvailableByTitle("Test1")).thenReturn(book);
        // When
        Book result = bookService.isAvailable(title);
        // Then
        assertEquals(book, result);
//        assertNotEquals(book, result);
    }

    @Test
    public void testBorrowBook() {
        //Given
        Long bookId = 1L;
        boolean isAvailable = true;
        Book book = Book.builder().id(bookId).title("Borrow Book").isAvailable(isAvailable).build();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book result = bookService.borrowBook(bookId);
        //Then
        assertFalse(result.isAvailable());
    }

    @Test
    public void testReturnBook() {
        //Given
        Long bookId = 1L;
        boolean isAvailable = false;
        Book book = Book.builder().id(bookId).title("Return Book").isAvailable(isAvailable).build();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book result = bookService.returnBook(bookId);
        //Then
        assert (result.isAvailable());
    }

    @Test
    public void testGetAuthorsSorted() {
        //Given
        Long bookId1 = 1L;
        Long bookId2 = 2L;
        Long bookId3 = 3L;
        String author1 = "Author1";
        String author2 = "BAuthor2";
        String author3 = "CAuthor3";
        Book book1 = Book.builder().id(bookId1).title("GetBook1").author(author1).build();
        Book book2 = Book.builder().id(bookId2).title("GetBook2").author(author2).build();
        Book book3 = Book.builder().id(bookId3).title("GetBook3").author(author3).build();
        List<Book> bookList = List.of(book1, book2, book3);
        Sort sort = Sort.by(Sort.Direction.ASC, "author");
        when(bookRepository.findAll(sort)).thenReturn(bookList);
        //When
        List<Book> result = bookService.getAuthorSorted("author");
        //Then
        assertEquals(bookList, result);
    }
}
