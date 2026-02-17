package com.ayush.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender mailSender;
	
	 public void sendReminderEmail(String to,String title,String description) {

	        SimpleMailMessage message =
	                new SimpleMailMessage();

	        message.setTo(to);
	        message.setSubject("RemindFlow Reminder");
	        message.setText(
	                "Reminder: " + title +
	                "\n\n" + description);

	        mailSender.send(message);
	 }

}
