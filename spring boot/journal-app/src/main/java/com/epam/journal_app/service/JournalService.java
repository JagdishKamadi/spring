package com.epam.journal_app.service;

import com.epam.journal_app.model.Journal;

import java.util.List;

public interface JournalService {
    Journal get(final Integer id);

    List<Journal> getAll();

    Journal save(Journal journal);

    String delete(final Integer id);

    Journal update(Journal journal, final Integer id);
}
