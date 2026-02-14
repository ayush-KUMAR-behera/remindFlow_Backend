package com.ayush.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @GetMapping("/test")
    public String test(Authentication authentication) {

        return "Hello " + authentication.getName()
                + " — JWT is working!";
    }
}
