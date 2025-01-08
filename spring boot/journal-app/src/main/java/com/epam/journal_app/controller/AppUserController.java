package com.epam.journal_app.controller;

import com.epam.journal_app.model.AppUser;
import com.epam.journal_app.model.TokenResponse;
import com.epam.journal_app.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(appUserService.create(appUser), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(appUserService.login(appUser), HttpStatus.OK);
    }

}
