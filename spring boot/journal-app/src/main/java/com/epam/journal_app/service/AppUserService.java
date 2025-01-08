package com.epam.journal_app.service;

import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.model.TokenResponse;
import com.epam.journal_app.repository.AppUserRepository;
import com.epam.journal_app.security_service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private static final Logger LOG = LogManager.getLogger(AppUserService.class);

    private final AppUserRepository appUserRepository;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final UserDetailsServiceImpl userDetailsService;

    @Value("${hashing_round}")
    private Integer hashingRound;

    public AppUserService(AppUserRepository appUserRepository, EmailService emailService, AuthenticationManager authenticationManager, JWTService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.appUserRepository = appUserRepository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public AppUser create(AppUser appUser) {
        String subject = "Regarding account creation";
        String body = "Hi " + appUser.getFullName() + ", Your account has created";
        emailService.sendMail(appUser.getEmail(), subject, body);
        appUser.setPassword(new BCryptPasswordEncoder(hashingRound).encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public TokenResponse login(AppUser appUser) {
        String jwtToken = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(appUser.getEmail());
            jwtToken = jwtService.generateToken(userDetails.getUsername());
        } catch (ExpiredJwtException e) {
            LOG.error("Your token has expired {}", e.getClass());
        } catch (Exception e) {
            LOG.error("Facing some internal issue {}", e.getMessage());
        }
        return new TokenResponse(jwtToken);
    }
}
