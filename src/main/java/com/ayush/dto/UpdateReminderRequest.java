package com.ayush.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReminderRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime reminderTime;
}
