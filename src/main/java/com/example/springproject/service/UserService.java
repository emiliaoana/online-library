package com.example.springproject.service;

import com.example.springproject.model.Book;
import com.example.springproject.model.BorrowHistory;
import com.example.springproject.model.User;
import com.example.springproject.repository.BookRepository;
import com.example.springproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookService bookService;
    private final BorrowHistoryService borrowHistoryService;
    private final BookRepository bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<Book> getBookByUserId(Long id) {
        return userRepository.findById(id).orElseThrow().getBookList();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User saveBook(Long idUser, Long bookId) {
        User user = userRepository.findById(idUser).orElseThrow();
        if (user.getBookList().stream().map(Book::getId).anyMatch(id -> id.equals(bookId))) {
            return null;
        }
        user.getBookList().add(bookService.getById(bookId));
        bookService.borrowBook(bookId);
        borrowHistoryService.saveBorrow(idUser, bookId);
        return userRepository.save(user);
    }

    public User deleteBook(Long idUser, Long bookId) {
        User user = userRepository.findById(idUser).orElseThrow();
        user.getBookList().remove(bookService.getById(bookId));
        return userRepository.save(user);
    }

    public List<Book> getAllBooksByUser(Long id) {
        return userRepository.findById(id).orElseThrow().getBookList();
    }
    //Book book = bookRepository.findById(bookId);
    //book.name
}
