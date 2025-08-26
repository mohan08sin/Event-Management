package com.eventmangment.DAO;

import com.eventmangment.Dto.ResponseStructure;
import com.eventmangment.Entity.Event;
import com.eventmangment.Entity.Organizer;
import com.eventmangment.Exceptions.IdNotFound;
import com.eventmangment.Repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;

@Repository
public class OrganizerDao {

    @Autowired
    private OrganizerRepo organizerRepo;

    public Organizer saveOrg(Organizer organizer){
        return organizerRepo.save(organizer);
    }


    public List<Organizer> getAllOrg(){
        return (List<Organizer>) organizerRepo.findAll();
    }

    public Optional<Organizer> getByIdO(Integer id){
        return organizerRepo.findById(id);
    }

    public Organizer deleteOrgByIdO(Integer id){
        Optional<Organizer> org = organizerRepo.findById(id);
        if(org.isPresent()){
            organizerRepo.deleteById(id);
            return org.get();
        }
        else {
            throw new IdNotFound("Id not found");
        }
    }

    public Optional<Organizer> updateOrgById(Integer id , Organizer newOrg){
        Optional<Organizer> oldOrg = organizerRepo.findById(id);
        if(oldOrg.isPresent()){
            newOrg.setId(id);
            Organizer updated = organizerRepo.save(newOrg);
            return Optional.ofNullable(updated);
        }
        else{
            throw new IdNotFound("Id not found for Updatation");
        }
    }

    public Page<Organizer> getOrganizersPaginatedAndSorted(int offset, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortBy));
        return organizerRepo.findAll(pageable);
    }

}
