package com.ayush.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.ayush.entity.Reminder;
import com.ayush.entity.User;
import com.ayush.util.ReminderStatus;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

//    Page<Reminder> findByUser(User user, Pageable pageable);
//
    @Query("""
        SELECT r FROM Reminder r
        JOIN FETCH r.user
        WHERE r.status = :status
        AND r.reminderTime <= :time
    """)
    List<Reminder> findPendingReminders(
            @Param("status") ReminderStatus status,
            @Param("time") LocalDateTime time
    );
//    
    Page<Reminder> findByUserAndActiveTrue(User user, Pageable pageable);

    List<Reminder> findByActiveTrueAndStatusAndReminderTimeBefore(
            ReminderStatus status,
            LocalDateTime time
    );

}
