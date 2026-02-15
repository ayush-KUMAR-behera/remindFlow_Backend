package com.ayush.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayush.dto.CreateReminderRequest;
import com.ayush.dto.ReminderResponse;
import com.ayush.entity.Reminder;
import com.ayush.entity.User;
import com.ayush.repository.ReminderRepository;
import com.ayush.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReminderService {
	
	private final ReminderRepository reminderRepository;
	private final UserRepository userRepository;
	
	public ReminderResponse createReminder(CreateReminderRequest request,String userEmail) {
		
		User user=userRepository.findByEmail(userEmail)
				.orElseThrow(()-> new RuntimeException("User not found"));
		

        Reminder reminder = new Reminder();
        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setUser(user);

        Reminder saved = reminderRepository.save(reminder);

        return mapToResponse(saved);
		
	}
	
	

    public List<ReminderResponse> getMyReminders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return reminderRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
	
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
