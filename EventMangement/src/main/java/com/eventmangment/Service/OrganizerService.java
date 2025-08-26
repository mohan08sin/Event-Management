package com.eventmangment.Service;


import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.DAO.OrganizerDao;
import com.eventmangment.Entity.Event;
import com.eventmangment.Entity.Organizer;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Exceptions.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerDao organizerDao;

    public ResponseEntity<ResponseStructure<Organizer>> saveOrganiser(Organizer organizer){

        ResponseStructure<Organizer> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Organiser Data Saved");
        res.setData(organizerDao.saveOrg(organizer));
        return new ResponseEntity<>(res , HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizers() {
        Optional<List<Organizer>> list = Optional.ofNullable(organizerDao.getAllOrg());
        ResponseStructure<List<Organizer>> res = new ResponseStructure<>();
        if (list.isPresent() && !list.get().isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Organizers Fetched");
            res.setData(list.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Organizer Found");
        }
    }

    public ResponseEntity<ResponseStructure<Organizer>> deleteOrgBYId(Integer id){
        Optional<Organizer> org = organizerDao.getByIdO(id);
        if(org.isPresent()){
            organizerDao.deleteOrgByIdO(id);
            ResponseStructure<Organizer> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Organizers Deleted");
            res.setData(org.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else{
            throw new IdNotFound("Id Not Found");
        }
    }

    public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Integer id , Organizer newOrg){
        Optional<Organizer> update = organizerDao.updateOrgById(id , newOrg);
        ResponseStructure<Organizer> res = new ResponseStructure<>();

        if (update.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Organizer updated successfully");
            res.setData(update.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Organizer ID not found for update");
        }
    }

    public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(Integer id) {
        Optional<Organizer> ev = organizerDao.getByIdO(id);
        ResponseStructure<Organizer> res = new ResponseStructure<>();
        if (ev.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Organizer Found");
            res.setData(ev.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("Organizer Not Found with ID: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Organizer>>> getPaginatedOrganizers(int offset, int pageSize, String sortBy) {
        Page<Organizer> page = organizerDao.getOrganizersPaginatedAndSorted(offset, pageSize, sortBy);

        ResponseStructure<List<Organizer>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Organizers fetched with pagination and sorting");
        res.setData(page.getContent());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
