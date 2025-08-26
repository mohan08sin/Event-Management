package com.eventmangment.DAO;

import com.eventmangment.Entity.Organizer;
import com.eventmangment.Entity.Registration;
import com.eventmangment.Entity.Venue;
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
public class VenueDao {

    @Autowired
    private VenueRepo venueRepo;

    public Venue saveVanue(Venue venue){
        return venueRepo.save(venue);
    }

    public List<Venue> getAllV(){
        return venueRepo.findAll();
    }

    public Optional<Venue> getByIdV(Integer id){
        return venueRepo.findById(id);
    }

    public Venue deleteByidV(Integer id){
        Optional<Venue> vew = venueRepo.findById(id);
        if(vew.isPresent()){
            venueRepo.deleteById(id);
            return vew.get();
        }
        else{
            throw new IdNotFound("Id not Found for deleting");
        }
    }

    public Optional<Venue> updateVenueByid(Integer id , Venue newVenue){
        Optional<Venue> oldVen = venueRepo.findById(id);
        if(oldVen.isPresent()){
            newVenue.setId(id);
            Venue updated = venueRepo.save(newVenue);
            return Optional.ofNullable(updated);
        }
        else{
            throw new IdNotFound("Id Not Found for deletion");
        }
    }

    public List<Venue> getVenueByLocation(String location) {
        List<Venue> venues = venueRepo.findByLocation(location);
        if (venues.isEmpty()) {
            throw new IdNotFound("No venues found at location: " + location);
        }
        return venues;
    }

    public Page<Venue> getVenuesByPaginationAndSorting(int offset, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortBy));
        return venueRepo.findAll(pageable);
    }
}
