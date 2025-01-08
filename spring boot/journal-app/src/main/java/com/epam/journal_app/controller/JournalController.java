package com.epam.journal_app.controller;

import com.epam.journal_app.model.Journal;
import com.epam.journal_app.service.JournalServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/journal")
public class JournalController {

    private static final Logger LOGGER = LogManager.getLogger(JournalController.class);

    private final JournalServiceImpl journalServiceImpl;

    public JournalController(JournalServiceImpl journalServiceImpl) {
        this.journalServiceImpl = journalServiceImpl;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Journal> get(@PathVariable Integer id) {
        return new ResponseEntity<>(journalServiceImpl.get(id), HttpStatus.OK);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<Journal>> getAll() {
        LOGGER.info("This info message is going to ignore as default logging change to ERROR for this class");
        return new ResponseEntity<>(journalServiceImpl.getAll(), HttpStatus.OK);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping
    public ResponseEntity<Journal> save(@RequestBody Journal journal) {
        return new ResponseEntity<>(journalServiceImpl.save(journal), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(journalServiceImpl.delete(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Journal> update(@RequestBody Journal journal, @PathVariable Integer id) {
        return new ResponseEntity<>(journalServiceImpl.update(journal, id), HttpStatus.OK);
    }
}
