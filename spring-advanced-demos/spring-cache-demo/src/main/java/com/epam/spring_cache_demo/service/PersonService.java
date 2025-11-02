package com.epam.spring_cache_demo.service;

import com.epam.spring_cache_demo.cache.PersonLRUCache;
import com.epam.spring_cache_demo.model.Person;
import com.epam.spring_cache_demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonLRUCache<Integer, Person> personLRUCache;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personLRUCache = new PersonLRUCache<>(3);
    }

    public Person getPerson(Integer id) {
        if (personLRUCache.containsKey(id)) {
            log.info("\n ++|| Calling from Person LRU Cache with ID " + id + " ||++\n");
            return personLRUCache.get(id);
        } else {
            log.info("\n ++|| Calling from database with ID " + id + " ||++\n");
            Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found with this id " + id));
            personLRUCache.put(id, person);
            log.info("\n ++|| Cache Data " + personLRUCache + " ||++\n");
            return person;
        }
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
