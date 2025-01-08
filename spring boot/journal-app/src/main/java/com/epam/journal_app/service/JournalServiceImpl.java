package com.epam.journal_app.service;

import com.epam.journal_app.exception.JournalNotExistsException;
import com.epam.journal_app.exception.NotAuthorizedException;
import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.model.Journal;
import com.epam.journal_app.repository.AppUserRepository;
import com.epam.journal_app.repository.JournalRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JournalServiceImpl implements JournalService {

    public static final String JOURNAL_NOT_EXIST_FOR_THIS_ID = "Journal not exist for this id";
    private final JournalRepository journalRepository;
    private final AppUserRepository userRepository;

    public JournalServiceImpl(JournalRepository journalRepository, AppUserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Journal get(final Integer id) {
        Journal journal = journalRepository.findById(id).orElseThrow(() -> new JournalNotExistsException(JOURNAL_NOT_EXIST_FOR_THIS_ID + id));
        if (!Objects.equals(journal.getAppUserId(), getCurrentLoggedInUserId())) {
            throw new NotAuthorizedException();
        }
        return journal;
    }

    @Override
    public List<Journal> getAll() {
        return journalRepository.findAll();
    }

    @Override
    public Journal save(Journal journal) {
        journal.setAppUserId(getCurrentLoggedInUserId());
        return journalRepository.save(journal);
    }

    @Override
    public String delete(final Integer id) {
        Journal journal = journalRepository.findById(id).orElseThrow(() -> new JournalNotExistsException(JOURNAL_NOT_EXIST_FOR_THIS_ID + id));

        if (!Objects.equals(journal.getAppUserId(), getCurrentLoggedInUserId())) {
            throw new NotAuthorizedException();
        }

        journalRepository.deleteById(id);

        return "Record deleted for id " + id;
    }

    @Override
    public Journal update(Journal journal, final Integer id) {

        Journal tempJournal = journalRepository.findById(id).orElseThrow(() -> new JournalNotExistsException(JOURNAL_NOT_EXIST_FOR_THIS_ID + id));
        if (!Objects.equals(tempJournal.getAppUserId(), getCurrentLoggedInUserId())) {
            throw new NotAuthorizedException();
        }

        tempJournal.setTitle(!journal.getTitle().isBlank() ? journal.getTitle() : tempJournal.getTitle());
        tempJournal.setContent(!journal.getContent().isBlank() ? journal.getContent() : tempJournal.getContent());

        return journalRepository.save(tempJournal);
    }

    public Integer getCurrentLoggedInUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        // this will give the username to us
        String username = context.getAuthentication().getName();
        AppUser appUser = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return appUser.getId();
    }
}
