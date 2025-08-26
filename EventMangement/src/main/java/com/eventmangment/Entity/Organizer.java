package com.eventmangment.Entity;
import com.eventmangment.Entity.Event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String organization;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;
}
