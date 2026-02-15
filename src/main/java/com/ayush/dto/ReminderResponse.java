package com.ayush.dto;

import java.time.LocalDateTime;

import com.ayush.util.ReminderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReminderResponse {
	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime reminderTime;
	private ReminderStatus reminderStatus;

}
