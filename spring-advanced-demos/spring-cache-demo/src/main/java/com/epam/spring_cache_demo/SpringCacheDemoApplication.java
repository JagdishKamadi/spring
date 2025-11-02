package com.epam.spring_cache_demo;

import com.epam.spring_cache_demo.model.Person;
import com.epam.spring_cache_demo.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCacheDemoApplication implements CommandLineRunner {


    private final PersonService personService;

    public SpringCacheDemoApplication(PersonService personService) {
        this.personService = personService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Person p1 = Person.builder()
                .firstName("Steven")
                .lastName("Fiegn")
                .build();

        Person p2 = Person.builder()
                .firstName("Black")
                .lastName("Widow")
                .build();

        Person p3 = Person.builder()
                .firstName("Tony")
                .lastName("Stark")
                .build();

        Person p4 = Person.builder()
                .firstName("Steve")
                .lastName("Roggers")
                .build();

        Person p5 = Person.builder()
                .firstName("Wanda")
                .lastName("Vision")
                .build();

        personService.savePerson(p1);
        personService.savePerson(p2);
        personService.savePerson(p3);
        personService.savePerson(p4);
        personService.savePerson(p5);
    }
}
