package com.eventmangment.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Long contact;

    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Registration> registrations;
}

