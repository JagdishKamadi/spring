package com.epam.service;

import com.epam.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();

    List<Book> findByTitleContaining(String title);

    Book findById(Long id);

    void delete(Long id);

    Book Update(Book book, Long id);
}
