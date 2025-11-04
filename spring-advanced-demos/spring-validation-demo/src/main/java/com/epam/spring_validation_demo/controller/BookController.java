package com.epam.spring_validation_demo.controller;

import com.epam.spring_validation_demo.model.Book;
import com.epam.spring_validation_demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> error = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }
}
