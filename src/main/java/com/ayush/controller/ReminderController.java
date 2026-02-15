package com.ayush.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ayush.dto.CreateReminderRequest;
import com.ayush.dto.ReminderResponse;
import com.ayush.service.ReminderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

	private final ReminderService reminderService;
	
	@PostMapping
	public ReminderResponse createReminder(@Valid @RequestBody CreateReminderRequest request,
			Authentication authentication) {
		
		String email=authentication.getName();
		return reminderService.createReminder(request, email);
		
	}
    
	@GetMapping
	public List<ReminderResponse> getMyReminders(Authentication authentication){
		
		String email=authentication.getName();
		
		return reminderService.getMyReminders(email);
		
	}
}
