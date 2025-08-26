package com.eventmangment.Repository;


import com.eventmangment.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepo extends JpaRepository<Venue, Integer> {

    List<Venue> findByLocation(String location);
}
