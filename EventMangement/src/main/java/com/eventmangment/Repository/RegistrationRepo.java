package com.eventmangment.Repository;

import com.eventmangment.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepo extends JpaRepository<Registration , Integer> {
    List<Registration> findByEventId(Integer eventId);

    List<Registration> findByAttendeeId(Integer attendeeId);

}
