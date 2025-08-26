package com.eventmangment.DAO;

import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Attendee;
import com.eventmangment.Entity.Event;
import com.eventmangment.Entity.Organizer;
import com.eventmangment.Entity.Registration;
import com.eventmangment.Exceptions.CustomException;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationDao {

    @Autowired
    private RegistrationRepo registrationRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private AttendeeRepo attendeeRepo;



    public Registration saveReg(Registration registration){
        if(registration.getAttendee() != null && registration.getAttendee().getId() != null){
            Optional<Attendee> attendee = attendeeRepo.findById(registration.getAttendee().getId());
            if(attendee.isPresent()){
                registration.setAttendee(attendee.get());
            }
            else{
                throw new IdNotFound("Attende Not found");
            }
        }
        else{
            throw new CustomException("Passing id is pending");
        }

        if(registration.getEvent() != null && registration.getEvent().getId() != null){
            Optional<Event> event = eventRepo.findById(registration.getEvent().getId());
            if(event.isPresent()){
                registration.setEvent(event.get());
            }
            else{
                throw new IdNotFound("Event Not found");
            }
        }
        else{
            throw new CustomException("Give the id");
        }

        return registrationRepo.save(registration);

    }

    public List<Registration> getAllR(){

        return registrationRepo.findAll();
    }

    public Optional<Registration> getByIdR(Integer id){
        return registrationRepo.findById(id);
    }

    public Registration deleteByidR(Integer id){
        Optional<Registration> reg = registrationRepo.findById(id);
        if(reg.isPresent()){
            registrationRepo.deleteById(id);
            return reg.get();
        }
        else{
            throw new IdNotFound("Id not found for deleteing");
        }
    }

    public Optional<Registration> upadteRegById(Integer id , Registration newReg){
        Optional<Registration> oldReg = registrationRepo.findById(id);
        if(oldReg.isPresent()){
            newReg.setId(id);
            Registration updated = registrationRepo.save(newReg);
            return Optional.ofNullable(updated);
        }
        else{
            throw new IdNotFound("Id Not Found For Updatation");
        }
    }

    public List<Registration> getRegistrationsByEventId(Integer eventId) {
        List<Registration> regs = registrationRepo.findByEventId(eventId);
        if (regs.isEmpty()) {
            throw new IdNotFound("No registrations found for Event ID: " + eventId);
        }
        return regs;
    }

    public List<Registration> getRegistrationsByAttendeeId(Integer attendeeId) {
        List<Registration> registrations = registrationRepo.findByAttendeeId(attendeeId);
        if (registrations.isEmpty()) {
            throw new IdNotFound("No registrations found for Attendee ID: " + attendeeId);
        }
        return registrations;
    }

}
