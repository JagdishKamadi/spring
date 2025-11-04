package com.epam.spring_validation_demo.controller;

import com.epam.spring_validation_demo.model.Book;
import com.epam.spring_validation_demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }
}
