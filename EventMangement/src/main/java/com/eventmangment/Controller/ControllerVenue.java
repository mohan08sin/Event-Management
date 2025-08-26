package com.eventmangment.Controller;

import com.eventmangment.Entity.Registration;
import com.eventmangment.Service.VenueService;
import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/V")
public class ControllerVenue {

    @Autowired
    private VenueService venueService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Venue>> saveVanue(@RequestBody Venue venue){
        return venueService.saveVanue(venue);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Venue>>> getALlVenue(){
        return venueService.getAllVenues();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Venue>> updateVenue(@PathVariable Integer id , @RequestBody Venue newVenue){
        return venueService.updateVenue(id , newVenue);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<Venue>> deleteVenue(@PathVariable Integer id){
        return venueService.deleteVenueById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Venue>> getVenueById(@PathVariable Integer id){
        return venueService.getVenueById(id);
    }

    @GetMapping("/by-location/{location}")
    public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(@PathVariable String location) {
        return venueService.getVenueByLocation(location);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<List<Venue>>> getPaginatedVenues(
            @RequestParam int offset,
            @RequestParam int pageSize,
            @RequestParam String sortBy
    ) {
        return venueService.getPaginatedVenues(offset, pageSize, sortBy);
    }


}
