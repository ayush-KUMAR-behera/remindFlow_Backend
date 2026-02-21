package com.ayush.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ayush.dto.CreateReminderRequest;
import com.ayush.dto.ReminderResponse;
import com.ayush.dto.UpdateReminderRequest;
import com.ayush.service.ReminderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping
    public ReminderResponse createReminder(
            @Valid @RequestBody CreateReminderRequest request,
            Authentication authentication) {

        return reminderService.createReminder(
                request,
                authentication.getName());
    }

    @GetMapping
    public Page<ReminderResponse> getMyReminders(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return reminderService.getMyReminders(
                authentication.getName(),
                page,
                size);
    }

    @PutMapping("/{id}")
    public ReminderResponse updateReminder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReminderRequest request,
            Authentication authentication) {

        return reminderService.updateReminder(
                id,
                request,
                authentication.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteReminder(
            @PathVariable Long id,
            Authentication authentication) {

        reminderService.deleteReminder(
                id,
                authentication.getName());

        return "Reminder deleted successfully";
    }
}
