package com.udacity.jdnd.course3.critter.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    private String phoneNumber;

    private String notes;

//    @OneToMany(mappedBy = "customer",targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Long> petIds;

    @OneToMany(targetEntity = Pet.class, cascade=CascadeType.ALL)
    private List<Pet> pets;

    public Customer(Long id, String name) {
        super(id, name);
    }


    public Customer() {
        super();
        pets = new ArrayList<Pet>();
    }

    public void addPetToList(Pet pet) {
       pets.add(pet);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
