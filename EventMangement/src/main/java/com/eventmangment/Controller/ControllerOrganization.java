package com.eventmangment.Controller;

import com.eventmangment.Entity.Event;
import com.eventmangment.Service.OrganizerService;
import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/O")
//@CrossOrigin("")
public class ControllerOrganization {

    @Autowired
    private OrganizerService organizerService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Organizer>> saveOrg(@RequestBody Organizer organizer){
        return organizerService.saveOrganiser(organizer);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Organizer>>> getALlOrganiser(){
        return organizerService.getAllOrganizers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(@PathVariable Integer id , @RequestBody Organizer newOrganizer){
        return organizerService.updateOrganizer(id , newOrganizer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<Organizer>> deleteOrganizer(@PathVariable Integer id){
        return organizerService.deleteOrgBYId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Organizer>> getOrganizerById(@PathVariable Integer id){
        return organizerService.getOrganizerById(id);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<List<Organizer>>> getPaginatedOrganizers(
            @RequestParam int offset,
            @RequestParam int pageSize,
            @RequestParam String sortBy
    ) {
        return organizerService.getPaginatedOrganizers(offset, pageSize, sortBy);
    }

}
