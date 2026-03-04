package com.epam.validation.rule;


import com.epam.model.AppUser;

public interface ValidationRule {
    void apply(AppUser appUser);
}
