package com.epam.service;

import com.epam.model.AppUser;
import com.epam.repository.UserRepository;
import com.epam.validation.rule.ValidationRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final List<ValidationRule> validationRules;

    public UserService(UserRepository userRepository, List<ValidationRule> validationRules) {
        this.userRepository = userRepository;
        this.validationRules = validationRules;
    }

    public AppUser saveUser(AppUser appUser) {
        validationRules.forEach(rule -> rule.apply(appUser));
        return userRepository.save(appUser);
    }
}