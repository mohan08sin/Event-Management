package com.eventmangment.Controller;

import com.eventmangment.Service.AttendeeService;
import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Attendee;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/A")
public class ControllerAttendee {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Attendee>> saveAtt(@RequestBody Attendee attendee){
        return attendeeService.saveAttende(attendee);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttende(){
        return attendeeService.getAllAttendees();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Attendee>> updateAttende(@PathVariable Integer id ,@RequestBody Attendee newAttendee){
        return attendeeService.updateAtt(id , newAttendee);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<Attendee>> deleteAttende(@PathVariable Integer id){
        return attendeeService.deleteAttById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Attendee>> getAttendeById(@PathVariable Integer id){
        return attendeeService.getAttById(id);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeByEventId(@PathVariable Integer id){
        return attendeeService.getAttendeesByEventId(id);
    }

    @GetMapping("/contact/{contact}")
    public ResponseEntity<ResponseStructure<Attendee>> getByContact(@PathVariable Long contact) {
        return attendeeService.getAttendeeByContact(contact);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<List<Attendee>>> getPaginatedAttendees(
            @RequestParam int offset,
            @RequestParam int pageSize,
            @RequestParam String sortBy
    ) {
        return attendeeService.getPaginatedAttendees(offset, pageSize, sortBy);
    }



}
