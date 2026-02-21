package com.ayush.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.ayush.dto.CreateReminderRequest;
import com.ayush.dto.ReminderResponse;
import com.ayush.dto.UpdateReminderRequest;
import com.ayush.entity.Reminder;
import com.ayush.entity.User;
import com.ayush.exception.BadRequestException;
import com.ayush.exception.ResourceNotFoundException;
import com.ayush.repository.ReminderRepository;
import com.ayush.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;

    // ===============================
    // CREATE REMINDER
    // ===============================
    public ReminderResponse createReminder(
            CreateReminderRequest request,
            String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Reminder reminder = new Reminder();
        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setUser(user);
        reminder.setActive(true);

        Reminder saved = reminderRepository.save(reminder);

        return mapToResponse(saved);
    }

    // ===============================
    // GET MY REMINDERS (PAGINATION)
    // ===============================
    public Page<ReminderResponse> getMyReminders(
            String email,
            int page,
            int size) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Page<Reminder> reminders =
                reminderRepository.findByUserAndActiveTrue(
                        user,
                        PageRequest.of(page, size));

        return reminders.map(this::mapToResponse);
    }

    // ===============================
    // UPDATE REMINDER
    // ===============================
    public ReminderResponse updateReminder(
            Long reminderId,
            UpdateReminderRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not allowed");
        }

        if (!reminder.isActive()) {
            throw new BadRequestException("Reminder is deleted");
        }

        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderTime(request.getReminderTime());

        Reminder updated = reminderRepository.save(reminder);

        return mapToResponse(updated);
    }

    // ===============================
    // SOFT DELETE REMINDER
    // ===============================
    public void deleteReminder(Long reminderId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Not allowed");
        }

        reminder.setActive(false); // SOFT DELETE

        reminderRepository.save(reminder);
    }

    // ===============================
    // MAPPER
    // ===============================
    private ReminderResponse mapToResponse(Reminder reminder) {

        return new ReminderResponse(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getDescription(),
                reminder.getReminderTime(),
                reminder.getStatus()
        );
    }
}
