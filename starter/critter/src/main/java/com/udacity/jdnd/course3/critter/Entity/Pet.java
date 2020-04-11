package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType petType;

    private String name;

    private LocalDate birthDate;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private long ownerId;
}
