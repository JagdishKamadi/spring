package com.epam.validation;

import com.epam.exception.ValidationException;
import com.epam.model.AppUser;
import com.epam.validation.rule.ValidationRule;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidationRule implements ValidationRule {

    @Override
    public void apply(AppUser appUser) {

        if (!appUser.getPhoneNumber().startsWith("+91")) {
            throw new ValidationException("Phone number must start with +91", "user.phoneNumber");
        }
    }
}