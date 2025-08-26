package com.eventmangment.DAO;

import com.eventmangment.Entity.Event;
import com.eventmangment.Entity.Organizer;
import com.eventmangment.Entity.Venue;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Repository.EventRepo;
import com.eventmangment.Repository.OrganizerRepo;
import com.eventmangment.Repository.VenueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventDao {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private OrganizerRepo organizerRepo;

    @Autowired
    private VenueRepo venueRepo;

    public Event saveEvent(Event event){

        if(event.getOrganizer() != null && event.getOrganizer().getId() != null){
            Optional<Organizer> organizer = organizerRepo.findById(event.getOrganizer().getId());
            if(organizer.isPresent()){
                event.setOrganizer(organizer.get());
            }
            else {
                throw new IdNotFound("Organizer not found");
            }
        }
        else {
            throw new IdNotFound("Organizer id required");
        }

        if(event.getVenue() != null && event.getVenue().getId() != null){
            Optional<Venue> venue = venueRepo.findById(event.getVenue().getId());
            if(venue.isPresent()){
                event.setVenue(venue.get());
            }
            else {
                throw new IdNotFound("Venue not found");
            }
        }
        else {
            throw new IdNotFound("Venue id required");
        }
        return eventRepo.save(event);
    }



    public List<Event> getAllE(){
        return eventRepo.findAll();
    }

    public Optional<Event> getByidE(Integer id){
        return eventRepo.findById(id);
    }

    public Event deleteEvtByIdE(Integer id){
        Optional<Event> event = eventRepo.findById(id);
        if(event.isPresent()){
            eventRepo.deleteById(id);
            return event.get();
        }
        else{
            throw new IdNotFound("Id not found for deleteing");
        }
    }

    public Optional<Event> updateEventById(Integer id , Event newEvent){
        Optional<Event> oldEvent = eventRepo.findById(id);
        if(oldEvent.isPresent()){
            newEvent.setId(id);
            Event updated = eventRepo.save(newEvent);
            return Optional.ofNullable(updated);
        }
        else{
            throw new IdNotFound("Id not found for Updatation");
        }
    }

    public List<Event> eventByVenueId(Integer id) {
        List<Event> events = eventRepo.findByVenueId(id);
        if (events.isEmpty()) {
            throw new IdNotFound("No events found for Venue ID: " + id);
        }
        return events;
    }

    public List<Event> getEventsByOrganizerId(Integer orgId) {
        List<Event> events = eventRepo.findByOrganizerId(orgId);
        if (events.isEmpty()) {
            throw new IdNotFound("No events found for Organizer ID: " + orgId);
        }
        return events;
    }

    public Page<Event> getEventsByPageAndSort(int offset, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortBy));
        return eventRepo.findAll(pageable);
    }
}
