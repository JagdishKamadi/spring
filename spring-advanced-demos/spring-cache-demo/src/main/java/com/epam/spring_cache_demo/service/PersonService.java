package com.epam.spring_cache_demo.service;

import com.epam.spring_cache_demo.model.Person;
import com.epam.spring_cache_demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PersonService {

    public static final String PERSON_ID_CACHE = "personIdCache";
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable(cacheNames = PERSON_ID_CACHE, key = "#id")
    public Person getPerson(Integer id) {
        // once the cache data with Id, next time this logs won't print
        log.info("\n ++|| Calling from database with ID " + id + " ||++\n");
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found with this id " + id));
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    /**
     * Caching is generally not required for this method.
     * <p>
     * The cache should only be updated when the method is frequently called or when
     * the saved data is expected to be retrieved immediately afterward.
     * If data is saved infrequently, caching here is unnecessary and provides little benefit.
     * <br><br>
     * It is more appropriate to apply caching on update operations rather than on create operations.
     * </p>
     *
     * @param person the {@link Person} object to be saved
     * @return the saved {@link Person} entity
     */
    @CachePut(cacheNames = PERSON_ID_CACHE, key = "#result.id")
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }


    /**
     * Updates an existing {@link Person} record in the database based on the given ID.
     * <p>
     * If the person exists, the provided non-null fields (first name, last name)
     * will be updated. After updating the record, the corresponding cache entry
     * (identified by the person's ID) will also be refreshed automatically.
     * </p>
     *
     * @param id     the ID of the person to be updated
     * @param person the person object containing updated details
     * @return the updated {@link Person} entity
     * @throws RuntimeException if no person is found with the given ID
     */
    @CachePut(cacheNames = PERSON_ID_CACHE, key = "#id")
    public Person updatePerson(Integer id, Person person) {
        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with this id " + id));
        if (Objects.nonNull(person.getFirstName())) {
            person1.setFirstName(person.getFirstName());
        }
        if (Objects.nonNull(person.getLastName())) {
            person1.setLastName(person.getLastName());
        }
        return personRepository.save(person1);
    }

    /**
     * Removes a {@link Person} record from both the database and the cache.
     * <p>
     * Once the person is deleted from the database, the corresponding cache entry
     * (identified by the given ID) must also be evicted to ensure data consistency.
     * </p>
     *
     * @param id the ID of the person to delete
     */
    @CacheEvict(cacheNames = PERSON_ID_CACHE, key = "#id")
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

}

