package com.ayush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.entity.Reminder;
import com.ayush.entity.User;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser(User user);
}
