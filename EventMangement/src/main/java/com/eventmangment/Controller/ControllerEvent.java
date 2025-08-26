package com.eventmangment.Controller;


import com.eventmangment.Service.EventService;
import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/E")
//@CrossOrigin("*")
public class ControllerEvent {

    @Autowired
    private EventService eventService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Event>> saveEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent(){
        return eventService.getAllEvent();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Event>> updateEvent(@PathVariable Integer id , @RequestBody Event newEvent){
        return eventService.updateEvent(id , newEvent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<Event>> deleteEvent(@PathVariable Integer id){
        return eventService.deleteEventById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Event>> getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }

    @GetMapping("/venue/{id}")
    public ResponseEntity<ResponseStructure<List<Event>>> eventByVenueId(Integer id){
        return eventService.eventByVenueId(id);
    }

    @GetMapping("/org/{orgId}")
    public ResponseEntity<ResponseStructure<List<Event>>> getEventsByOrgId(@PathVariable Integer orgId) {
        return eventService.getEventsByOrgId(orgId);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<List<Event>>> getPaginatedEvents(
            @RequestParam int offset,
            @RequestParam int pageSize,
            @RequestParam String sortBy
    ) {
        return eventService.getEventsPaginatedAndSorted(offset, pageSize, sortBy);
    }



}