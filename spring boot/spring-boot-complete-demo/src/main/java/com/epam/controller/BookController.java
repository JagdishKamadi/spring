package com.epam.controller;

import com.epam.model.Book;
import com.epam.service.BookService;
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

    @PostMapping(value = "/save")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Book>> findBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/findByTitle/{title}")
    public ResponseEntity<List<Book>> findByTitleContaining(@PathVariable String title) {
        return new ResponseEntity<>(bookService.findByTitleContaining(title), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.Update(book, id), HttpStatus.OK);
    }
}
