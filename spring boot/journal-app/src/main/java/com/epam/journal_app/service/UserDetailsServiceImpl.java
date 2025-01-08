package com.epam.journal_app.service;

import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.model.UserPrincipal;
import com.epam.journal_app.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository userRepository;

    public UserDetailsServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // remember email will be our username
        AppUser appUser = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found for user " + username));

        return new UserPrincipal(appUser);
    }
}
