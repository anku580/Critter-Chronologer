package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getAllByPets(Pet pet);
    List<Schedule> getAllByEmployees(Employee employee);
    List<Schedule> getAllByPetsIn(List<Pet> pets);
}
