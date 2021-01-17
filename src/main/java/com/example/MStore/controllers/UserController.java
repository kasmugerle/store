package com.example.MStore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MStore.dto.UserProfile;
import com.example.MStore.security.CurrentUser;
import com.example.MStore.security.UserPrincipal;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public UserProfile getUser(@CurrentUser UserPrincipal currentUser) {
        return new UserProfile(currentUser.getName(), currentUser.getUsername(), currentUser.getEmail());
    }
}
