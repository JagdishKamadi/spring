package com.epam.journal_app.controller;

import com.epam.journal_app.exception.JournalNotExistsException;
import com.epam.journal_app.model.Journal;
import com.epam.journal_app.service.JournalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * This is also the controller testing done by using mockito
 * reference : {@link //howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/}
 */
@ExtendWith(MockitoExtension.class)
class JournalControllerTest {

    @InjectMocks
    private JournalController journalController;

    @Mock
    private JournalServiceImpl journalService;

    private Journal journal;


    @BeforeEach
    public void setUpObject() {
        journal = new Journal();
        journal.setTitle("Drilling in Java");
        journal.setContent("Learn java in 60 minutes");
    }

    @Test
    void test_get_whenJournalIdExist() {
        when(journalService.get(anyInt())).thenReturn(journal);
        ResponseEntity<Journal> responseEntity = journalController.get(anyInt());

        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertAll("Asserting the all fields in journal class",
                () -> assertEquals("Drilling in Java", responseEntity.getBody().getTitle(), "Checking title"),
                () -> assertEquals("Learn java in 60 minutes", responseEntity.getBody().getContent(), "Checking content"));
    }

    @Test
    void test_get_whenJournalIdNotExist_thenMustThrowException() {
        when(journalService.get(anyInt())).thenThrow(JournalNotExistsException.class);

        assertThrows(JournalNotExistsException.class, () -> journalController.get(anyInt()));
    }

    @Test
    void test_getAll() {
        Journal journal1 = new Journal();
        journal1.setTitle("Welcome to python course");
        journal1.setContent("Learn python for ML and AI");

        List<Journal> journalList = List.of(journal, journal1);
        when(journalService.getAll()).thenReturn(journalList);
        ResponseEntity<List<Journal>> responseEntity = journalController.getAll();

        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertAll("Asserting the all fields in journal class",
                () -> assertEquals("Welcome to python course", responseEntity.getBody().get(1).getTitle(), "Checking title"),
                () -> assertEquals("Learn python for ML and AI", responseEntity.getBody().get(1).getContent(), "Checking content"));
    }

    @Test
    void test_save() {
        when(journalService.save(any(Journal.class))).thenReturn(journal);
        ResponseEntity<Journal> responseEntity = journalController.save(journal);

        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertAll("Asserting the all fields in journal class",
                () -> assertEquals("Drilling in Java", responseEntity.getBody().getTitle(), "Checking title"),
                () -> assertEquals("Learn java in 60 minutes", responseEntity.getBody().getContent(), "Checking content"));
    }

    @Test
    void test_delete_whenJournalIdExist() {
        int id = 1;
        when(journalService.delete(id)).thenReturn("Record deleted for id 1");
        ResponseEntity<String> responseEntity = journalController.delete(id);
        assertEquals(HttpStatusCode.valueOf(204), responseEntity.getStatusCode());
        assertEquals("Record deleted for id 1", responseEntity.getBody());
    }

    @Test
    void test_delete_whenJournalIdNotExist() {
        int id = -11;
        when(journalService.delete(id)).thenThrow(JournalNotExistsException.class);
        assertThrows(JournalNotExistsException.class, () -> journalController.delete(id));
    }

    @Test
    void test_update_whenJournalIdExist() {
        journal.setId(1);

        Journal journal1 = new Journal();
        journal1.setId(1);
        journal1.setTitle("Welcome to python course");
        journal1.setContent("Learn python for ML and AI");

        when(journalService.update(journal, 1)).thenReturn(journal1);
        ResponseEntity<Journal> responseEntity = journalController.update(journal, 1);

        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertAll("Asserting the all fields in journal class",
                () -> assertEquals(1, responseEntity.getBody().getId(), "Checking id"),
                () -> assertEquals("Welcome to python course", responseEntity.getBody().getTitle(), "Checking title"),
                () -> assertEquals("Learn python for ML and AI", responseEntity.getBody().getContent(), "Checking content"));
    }

    @Test
    void test_update_whenJournalIdNotExist() {
        when(journalService.update(journal, -11)).thenThrow(JournalNotExistsException.class);
        assertThrows(JournalNotExistsException.class, () -> journalController.update(journal, -11));
    }
}
