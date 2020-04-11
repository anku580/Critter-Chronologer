package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {


    private Set<EmployeeSkill> skills;

    private Set<DayOfWeek> daysAvailable;
}
