package com.eventmangment.Service;

import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.DAO.EventDao;
import com.eventmangment.Entity.Attendee;
import com.eventmangment.Entity.Event;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Exceptions.NoRecordAvailableException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.id.OptimizableGenerator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public ResponseEntity<ResponseStructure<Event>> saveEvent(Event event){

        ResponseStructure<Event> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Event Data Saved");
        res.setData(eventDao.saveEvent(event));
        return new ResponseEntity<>(res , HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
        Optional<List<Event>> events = Optional.ofNullable(eventDao.getAllE());
        ResponseStructure<List<Event>> res = new ResponseStructure<>();
        if (events.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Events Fetched");
            res.setData(events.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Event Found");
        }
    }

    public ResponseEntity<ResponseStructure<Event>> deleteEventById(Integer id){
        Optional<Event> event = eventDao.getByidE(id);
        ResponseStructure<Event> res = new ResponseStructure<>();
        if(event.isPresent()){
            eventDao.deleteEvtByIdE(id);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Events Deleted");
            res.setData(event.get());
            return new ResponseEntity<>(res , HttpStatus.OK);
        }
        else{
            throw new IdNotFound("Id not found");
        }
    }

    public ResponseEntity<ResponseStructure<Event>> updateEvent(Integer id , Event newEvent){
        Optional<Event> update = eventDao.updateEventById(id , newEvent);
        ResponseStructure<Event> res = new ResponseStructure<>();

        if (update.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Event updated successfully");
            res.setData(update.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Event ID not found for update");
        }
    }


    public ResponseEntity<ResponseStructure<Event>> getEventById(Integer id) {
        Optional<Event> ev = eventDao.getByidE(id);
        ResponseStructure<Event> res = new ResponseStructure<>();
        if (ev.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Event Found");
            res.setData(ev.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("Event Not Found with ID: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Event>>> eventByVenueId(Integer id) {
        List<Event> eventList = eventDao.eventByVenueId(id);

        ResponseStructure<List<Event>> res = new ResponseStructure<>();

        if (!eventList.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Event Data Fetched By Venue");
            res.setData(eventList);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("No events found for Venue ID: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Event>>> getEventsByOrgId(Integer orgId) {
        List<Event> events = eventDao.getEventsByOrganizerId(orgId);

        ResponseStructure<List<Event>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Events fetched for Organizer ID: " + orgId);
        res.setData(events);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Event>>> getEventsPaginatedAndSorted(int offset, int pageSize, String sortBy) {
        Page<Event> page = eventDao.getEventsByPageAndSort(offset, pageSize, sortBy);

        ResponseStructure<List<Event>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Events fetched with pagination and sorting");
        res.setData(page.getContent());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

