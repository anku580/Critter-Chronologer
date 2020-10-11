package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee extends User implements Serializable {


    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Employee(Long id, String name) {
        super(id, name);
    }

    public Employee() {

    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(skills, employee.skills) &&
                Objects.equals(daysAvailable, employee.daysAvailable) &&
                Objects.equals(schedule, employee.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills, daysAvailable, schedule);
    }
}
