package com.ayush.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ayush.entity.Reminder;
import com.ayush.repository.ReminderRepository;
import com.ayush.service.EmailService;
import com.ayush.util.ReminderStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {
	
	private final ReminderRepository reminderRepository;
	private final EmailService emailService;
	
	@Transactional
	 @Scheduled(fixedRate = 60000) 
	public void processReminders() {

	    List<Reminder> reminders =
	            reminderRepository.findPendingReminders(
	                    ReminderStatus.PENDING,
	                    LocalDateTime.now());

	    for (Reminder reminder : reminders) {
	    	
	    	String cleanEmail=reminder.getUser().getEmail().trim();
	    	
	    	System.out.println("Sending email to: ["+cleanEmail+"]");

	        emailService.sendReminderEmail(
	                reminder.getUser().getEmail(),
	                reminder.getTitle(),
	                reminder.getDescription());

	        reminder.setStatus(ReminderStatus.COMPLETED);
	    }
	}

}
