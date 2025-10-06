package com.epam;

import com.epam.model.Book;
import com.epam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootCompleteDemoApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCompleteDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = List.of(Book.builder()
                        .title("Drilling in C++")
                        .author("Bjarne Stroustrup")
                        .build(),
                Book.builder()
                        .title("Java in 60 Minutes a Day")
                        .author("R. F. Raposa")
                        .build(),
                Book.builder()
                        .title("Rich Dad Poor Dad")
                        .author("Robert Kiyosaki")
                        .build()
        );

        books.forEach(bookService::save);
    }
}
