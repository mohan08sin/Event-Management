package com.eventmangment.Service;


import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.DAO.RegistrationDao;
import com.eventmangment.Entity.Organizer;
import com.eventmangment.Entity.Registration;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Exceptions.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    public ResponseEntity<ResponseStructure<Registration>> saveRegistration(Registration registration){
        ResponseStructure<Registration> res = new ResponseStructure<>();
        res.setData(registrationDao.saveReg(registration));
        res.setMessage("Registration Saved");
        res.setStatusCode(HttpStatus.CREATED.value());

        return new ResponseEntity<>(res , HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistrations() {
        Optional<Registration> list = Optional.ofNullable((Registration) registrationDao.getAllR());
        ResponseStructure<Registration> res = new ResponseStructure<>();
        if (list.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Registrations Fetched");
            res.setData(list.get());
            return new ResponseEntity<>((MultiValueMap<String, String>) res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Registrations Found");
        }
    }

    public ResponseEntity<ResponseStructure<Registration>> deleteRegById(Integer id){
        Optional<Registration> reg = registrationDao.getByIdR(id);
        ResponseStructure<Registration> res = new ResponseStructure<>();
        if(reg.isPresent()){
            registrationDao.deleteByidR(id);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Registrations Deleted");
            res.setData(reg.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else{
            throw new IdNotFound("Id not found");
        }
    }

    public ResponseEntity<ResponseStructure<Registration>> updateRegistration(Integer id , Registration newReg){
        Optional<Registration> update = registrationDao.upadteRegById(id , newReg);
        ResponseStructure<Registration> res = new ResponseStructure<>();

        if (update.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Registration updated successfully");
            res.setData(update.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Registration ID not found for update");
        }
    }

    public ResponseEntity<ResponseStructure<Registration>> getRegById(Integer id) {
        Optional<Registration> reg = registrationDao.getByIdR(id);
        ResponseStructure<Registration> res = new ResponseStructure<>();
        if (reg.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Registration Found");
            res.setData(reg.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("Registration Not Found with ID: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Registration>>> getRegsByEventId(Integer eventId) {
        List<Registration> regs = registrationDao.getRegistrationsByEventId(eventId);

        ResponseStructure<List<Registration>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Registrations fetched for Event ID: " + eventId);
        res.setData(regs);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Registration>>> getRegsByAttendeeId(Integer attendeeId) {
        List<Registration> regs = registrationDao.getRegistrationsByAttendeeId(attendeeId);

        ResponseStructure<List<Registration>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Registrations fetched for Attendee ID: " + attendeeId);
        res.setData(regs);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
