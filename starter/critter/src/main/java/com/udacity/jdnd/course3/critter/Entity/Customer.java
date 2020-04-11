package com.udacity.jdnd.course3.critter.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private List<Long> petIds;
}
