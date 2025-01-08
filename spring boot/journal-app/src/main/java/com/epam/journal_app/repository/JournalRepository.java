package com.epam.journal_app.repository;

import com.epam.journal_app.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {
    public Optional<Journal> findByIdAndAppUserId(Integer id, Integer userId);
}
