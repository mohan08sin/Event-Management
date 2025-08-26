package com.eventmangment.Repository;

import com.eventmangment.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepo extends JpaRepository<Attendee, Integer> {


    @Query("SELECT r.attendee FROM Registration r WHERE r.event.id = ?1")
    List<Attendee> findAttendeesByEventId(Integer eventId);


    Optional<Attendee> findByContact(Long contact);

}
