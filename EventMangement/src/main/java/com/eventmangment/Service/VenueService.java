package com.eventmangment.Service;

import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.DAO.VenueDao;
import com.eventmangment.Entity.Registration;
import com.eventmangment.Entity.Venue;
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
public class VenueService {

    @Autowired
    private VenueDao venueDao;

    public ResponseEntity<ResponseStructure<Venue>> saveVanue(Venue venue){

        ResponseStructure<Venue> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Venue Data Saved");
        res.setData(venueDao.saveVanue(venue));

        return new ResponseEntity<>(res , HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenues() {
        Optional<List<Venue>> list = Optional.ofNullable(venueDao.getAllV());
        ResponseStructure<List<Venue>> res = new ResponseStructure<>();
        if (list.isPresent() && !list.get().isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Venues Fetched");
            res.setData(list.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No Venues Found");
        }
    }

    public ResponseEntity<ResponseStructure<Venue>> deleteVenueById(Integer id){
        Optional<Venue> ven = venueDao.getByIdV(id);
        if(ven.isPresent()){
            ResponseStructure<Venue> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Venues Fetched");
            res.setData(ven.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else{
            throw new IdNotFound("Venue Id not Found");
        }
    }

    public ResponseEntity<ResponseStructure<Venue>> updateVenue(Integer id , Venue newVenue){
        Optional<Venue> update = venueDao.updateVenueByid(id , newVenue);
        ResponseStructure<Venue> res = new ResponseStructure<>();

        if (update.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Venue updated successfully");
            res.setData(update.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFound("Venue ID not found for update");
        }
    }

    public ResponseEntity<ResponseStructure<Venue>> getVenueById(Integer id) {
        Optional<Venue> reg = venueDao.getByIdV(id);
        ResponseStructure<Venue> res = new ResponseStructure<>();
        if (reg.isPresent()) {
            venueDao.deleteByidV(id);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Venue Found");
            res.setData(reg.get());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("Venue Not Found with ID: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(String location) {
        List<Venue> venues = venueDao.getVenueByLocation(location);

        ResponseStructure<List<Venue>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Venues found at location: " + location);
        res.setData(venues);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Venue>>> getPaginatedVenues(int offset, int pageSize, String sortBy) {
        Page<Venue> venuePage = venueDao.getVenuesByPaginationAndSorting(offset, pageSize, sortBy);

        ResponseStructure<List<Venue>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Venues fetched with pagination and sorting");
        res.setData(venuePage.getContent());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }



}