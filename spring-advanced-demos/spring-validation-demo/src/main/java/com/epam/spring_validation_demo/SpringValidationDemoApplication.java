package com.epam.spring_validation_demo;

import com.epam.spring_validation_demo.model.Book;
import com.epam.spring_validation_demo.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringValidationDemoApplication implements CommandLineRunner {


    private final BookRepository bookRepository;

    public SpringValidationDemoApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringValidationDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Book book1 = Book.builder()
                .bookName("Drilling in c++")
                .authorName("Bjarne Stroustrup")
                .build();

        Book book2 = Book.builder()
                .bookName("Spring Boot in Action")
                .authorName("Craig Walls")
                .build();

        Book book3 = Book.builder()
                .bookName("Effective Java")
                .authorName("Joshua Bloch")
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
}
