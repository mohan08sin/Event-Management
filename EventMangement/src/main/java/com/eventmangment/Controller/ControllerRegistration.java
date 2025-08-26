package com.eventmangment.Controller;

import com.eventmangment.Entity.Organizer;
import com.eventmangment.Service.RegistrationService;
import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/R")
public class ControllerRegistration {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Registration>> saveRegistration(@RequestBody Registration registration){
        return registrationService.saveRegistration(registration);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Registration>>> getALlRegistration(){
        return registrationService.getAllRegistrations();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Registration>> updateRegistration(@PathVariable Integer id , @RequestBody Registration newRegistration){
        return registrationService.updateRegistration(id , newRegistration);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<Registration>> deleteRegistration(@PathVariable Integer id){
        return registrationService.deleteRegById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Registration>> getRegistrationById(@PathVariable Integer id){
        return registrationService.getRegById(id);
    }

    @GetMapping("/by-event/{eventId}")
    public ResponseEntity<ResponseStructure<List<Registration>>> getRegsByEvent(@PathVariable Integer eventId) {
        return registrationService.getRegsByEventId(eventId);
    }

    @GetMapping("/by-attendee/{attendeeId}")
    public ResponseEntity<ResponseStructure<List<Registration>>> getRegsByAttendee(@PathVariable Integer attendeeId) {
        return registrationService.getRegsByAttendeeId(attendeeId);
    }




}
