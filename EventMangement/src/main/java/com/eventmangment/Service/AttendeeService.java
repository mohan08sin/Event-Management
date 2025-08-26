package com.eventmangment.Service;

import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.DAO.AttendeeDao;
import com.eventmangment.Entity.Attendee;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Exceptions.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeDao attendeeDao;

    public ResponseEntity<ResponseStructure<Attendee>> saveAttende(Attendee attendee){
        ResponseStructure<Attendee> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Attende Created");
        res.setData(attendeeDao.saveAtt(attendee));

        return new ResponseEntity<>(res , HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendees() {
        Optional<List<Attendee>> list = Optional.ofNullable(attendeeDao.getAllAt());
        ResponseStructure<List<Attendee>> res = new ResponseStructure<>();
        if (list.isPresent() && !list.get().isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Attendees Fetched");
            res.setData(list.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Attendees Found");
        }
    }

    public ResponseEntity<ResponseStructure<Attendee>> deleteAttById(Integer id){
        Optional<Attendee> opt = attendeeDao.getAttByIdA(id);
        if(opt.isPresent()){
            attendeeDao.deleteByid(id);
            ResponseStructure<Attendee> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Attende deleted");
            res.setData(opt.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Attende with id " + id + " not found");
        }
    }

    public ResponseEntity<ResponseStructure<Attendee>> updateAtt(Integer id, Attendee newAttendee) {
        Optional<Attendee> updated = attendeeDao.updateAttById(id, newAttendee);
        ResponseStructure<Attendee> res = new ResponseStructure<>();

        if (updated.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Attendee updated successfully");
            res.setData(updated.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Attendee ID not found for update");
        }
    }

    public ResponseEntity<ResponseStructure<Attendee>> getAttById(Integer id){
        Optional<Attendee> att = attendeeDao.getAttByIdA(id);

        if(att.isPresent()){
            ResponseStructure<Attendee> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Attende Found");
            res.setData(att.get());
            return new ResponseEntity<>(res , HttpStatus.OK);
        }
        else{
            throw new IdNotFound("Id Not Found With Id:" + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeesByEventId(Integer eventId) {
        List<Attendee> attendees = attendeeDao.findAttendeesByEventId(eventId);
        ResponseStructure<List<Attendee>> res = new ResponseStructure<>();

        if (!attendees.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Attendees fetched for Event ID: " + eventId);
            res.setData(attendees);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No attendees found for Event ID: " + eventId);
        }
    }

    public ResponseEntity<ResponseStructure<Attendee>> getAttendeeByContact(Long contact) {
        Optional<Attendee> opt = attendeeDao.getAttendeeByContact(contact);
        ResponseStructure<Attendee> res = new ResponseStructure<>();

        if (opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Attendee found with contact: " + contact);
            res.setData(opt.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("No attendee found with contact: " + contact);
        }
    }

    public ResponseEntity<ResponseStructure<List<Attendee>>> getPaginatedAttendees(int offset, int pageSize, String sortBy) {
        Page<Attendee> page = attendeeDao.getAttendeesByPaginationAndSorting(offset, pageSize, sortBy);

        ResponseStructure<List<Attendee>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Attendees fetched with pagination and sorting");
        res.setData(page.getContent());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
