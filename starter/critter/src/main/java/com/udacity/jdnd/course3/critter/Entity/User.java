package com.udacity.jdnd.course3.critter.Entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy =  InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
