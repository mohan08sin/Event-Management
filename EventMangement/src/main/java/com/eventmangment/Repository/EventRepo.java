package com.eventmangment.Repository;

import com.eventmangment.Entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event , Integer> {

    List<Event> findByVenueId(Integer venueId);

    List<Event> findByOrganizerId(Integer organizerId);

    Page<Event> findAll(Pageable pageable);

}
