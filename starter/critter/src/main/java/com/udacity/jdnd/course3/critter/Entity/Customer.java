package com.udacity.jdnd.course3.critter.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User implements Serializable {

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

    public List<Pet> getPets() { return pets;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(notes, customer.notes) &&
                Objects.equals(pets, customer.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, notes, pets);
    }
}
