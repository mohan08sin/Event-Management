package com.eventmangment.Repository;

import com.eventmangment.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepo extends JpaRepository<Organizer , Integer> {
}
