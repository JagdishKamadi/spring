package com.epam.journal_app.service;

import com.epam.journal_app.exception.JournalNotExistsException;
import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.model.Journal;
import com.epam.journal_app.repository.AppUserRepository;
import com.epam.journal_app.repository.JournalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JournalServiceImplTest {

    @InjectMocks
    private JournalServiceImpl journalService;

    @Mock
    private JournalRepository journalRepository;

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private Optional<Journal> journalOptional;

    Journal journal;

    private List<Journal> journalList;

    private Optional<AppUser> appUserOptional;

    private AppUser appUser;

    @BeforeEach
    void setUpObject() {
        journal = new Journal();
        journal.setId(1);
        journal.setTitle("Drilling in C++");
        journal.setContent("Learn Structure, Union and Pointers");
        journal.setAppUserId(11);

        journalOptional = Optional.ofNullable(journal);

        journalList = new ArrayList<>();
        journalList.add(journal);

        appUser = new AppUser();
        appUser.setId(11);
        appUser.setEmail("abc@example.com");
        appUser.setPassword(new BCryptPasswordEncoder(12).encode("1234"));

        appUserOptional = Optional.ofNullable(appUser);

        SecurityContextHolder.setContext(securityContext);
    }

    private void authenticateUser() {
        when(journalRepository.findById(anyInt())).thenReturn(journalOptional);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("abc@example.com");
    }

    @Test
    void test_get_whenJournalExistWithGivenId() {
        authenticateUser();
        when(userRepository.findByEmail("abc@example.com")).thenReturn(appUserOptional);
        assertEquals(journal, journalService.get(anyInt()));
    }

    @Test
    void test_get_whenJournalNotExistWithGivenId() {
        when(journalRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(JournalNotExistsException.class, () -> journalService.get(anyInt()));
    }

    @Test
    void test_getAll() {
        when(journalRepository.findAll()).thenReturn(journalList);
        assertEquals(journalList, journalService.getAll());
    }

    @Test
    void test_save() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("abc@example.com");
        when(userRepository.findByEmail("abc@example.com")).thenReturn(appUserOptional);
        when(journalRepository.save(journal)).thenReturn(journal);
        assertEquals(journal, journalService.save(journal));
    }

    @Test
    void test_delete_whenJournalExistWithGivenId() {
        authenticateUser();
        when(userRepository.findByEmail("abc@example.com")).thenReturn(appUserOptional);
        journalRepository.deleteById(anyInt());
        verify(journalRepository).deleteById(anyInt());
        assertEquals("Record deleted for id 0", journalService.delete(anyInt()));
    }

    @Test
    void test_delete_whenJournalNotExistWithGivenId_thenMustThrowException() {
        when(journalRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(JournalNotExistsException.class, () -> journalService.delete(anyInt()));
    }

    @Test
    void test_update_whenJournalExistWithGivenId() {
        authenticateUser();
        when(userRepository.findByEmail("abc@example.com")).thenReturn(appUserOptional);
        when(journalRepository.save(any(Journal.class))).thenReturn(journal);
        assertEquals(journal, journalService.update(journal, anyInt()));
    }

    @Test
    void test_update_whenJournalNotExistWithGivenId_thenMustThrowException() {
        when(journalRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(JournalNotExistsException.class, () -> journalService.update(journal, anyInt()));
    }

    @Test
    void test_getCurrentLoggedInUserId() {
        String username = "abc@example.com";
        when(securityContext.getAuthentication()).thenReturn(authentication);

        when(authentication.getName()).thenReturn(username);
        when(userRepository.findByEmail(username)).thenReturn(appUserOptional);

        assertEquals(11, journalService.getCurrentLoggedInUserId());
    }
}