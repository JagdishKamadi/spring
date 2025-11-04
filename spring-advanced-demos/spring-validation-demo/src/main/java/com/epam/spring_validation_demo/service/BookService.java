package com.epam.spring_validation_demo.service;

import com.epam.spring_validation_demo.model.Book;
import com.epam.spring_validation_demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book doesn't exist for this id " + id));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
