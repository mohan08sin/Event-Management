package com.eventmangment.DAO;

import com.eventmangment.Entity.Attendee;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AttendeeDao {

    @Autowired
    private AttendeeRepo attendeeRepo;

    public Attendee saveAtt(Attendee attendee) {
        return attendeeRepo.save(attendee);
    }

    public List<Attendee> getAllAt(){
        return attendeeRepo.findAll();
    }

    public Optional<Attendee> getAttByIdA(Integer id){
        return attendeeRepo.findById(id);
    }

    public Attendee deleteByid(Integer id) {
        Optional<Attendee> attendee = attendeeRepo.findById(id);
        if (attendee.isPresent()) {
            attendeeRepo.deleteById(id);
            return attendee.get();
        } else {
            throw new IdNotFound("Attende with id " + id + " not found");
        }
    }

    public Optional<Attendee> updateAttById(Integer id , Attendee newAttendee){
        Optional<Attendee> oldAttendee = attendeeRepo.findById(id);
        if(oldAttendee.isPresent()){
            newAttendee.setId(id);
            Attendee updated = attendeeRepo.save(newAttendee);
            return Optional.ofNullable(updated);
        }
        else {
            throw new IdNotFound("Id not found for Updatation");
        }
    }

    public List<Attendee> findAttendeesByEventId(Integer id) {
        return attendeeRepo.findAttendeesByEventId(id);
    }

    public Optional<Attendee> getAttendeeByContact(Long contact) {
        return attendeeRepo.findByContact(contact);
    }

    public Page<Attendee> getAttendeesByPaginationAndSorting(int offset, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortBy));
        return attendeeRepo.findAll(pageable);
    }

}
