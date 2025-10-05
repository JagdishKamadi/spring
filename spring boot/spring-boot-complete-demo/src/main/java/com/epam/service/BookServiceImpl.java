package com.epam.service;

import com.epam.exception.BookNotFoundException;
import com.epam.model.Book;
import com.epam.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitleContaining(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found for this id " + id));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book Update(Book book, Long id) {
        Optional<Book> book1 = bookRepository.findById(id);
        book1.ifPresent(b -> {
            if (!Objects.isNull(book.getTitle())) {
                b.setTitle(book.getTitle());
            }
            if (!Objects.isNull(book.getAuthor())) {
                b.setAuthor(book.getAuthor());
            }
        });

        return book1.map(bookRepository::save).orElseThrow(() -> new BookNotFoundException("Book not found for this id " + id));
    }
}
