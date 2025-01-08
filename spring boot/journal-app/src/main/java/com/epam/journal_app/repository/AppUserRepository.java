package com.epam.journal_app.repository;

import com.epam.journal_app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

    @Query("SELECT u FROM AppUser u WHERE u.lastModifiedDateTime < :threshold")
    List<AppUser> findUsersNotUpdatedSince(@Param("threshold") LocalDateTime threshold);
}
