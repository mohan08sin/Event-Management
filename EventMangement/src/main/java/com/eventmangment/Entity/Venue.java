package com.eventmangment.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private String company;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;
}

