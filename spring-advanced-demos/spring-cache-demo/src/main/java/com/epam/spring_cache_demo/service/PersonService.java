package com.epam.spring_cache_demo.service;

import com.epam.spring_cache_demo.model.Person;
import com.epam.spring_cache_demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable(cacheNames = "personIdCache", key = "#id")
    public Person getPerson(Integer id) {
        // once the cache data with Id, next time this logs won't print
        log.info("\n ++|| Calling from database with ID " + id + " ||++\n");
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found with this id " + id));
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
