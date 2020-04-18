package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Schedule createSchedule(Schedule schedule, List<Long> employeeList, List<Long> petList) {

        List<Employee> employees = employeeRepository.findAllById(employeeList);
        List<Pet> pets = petRepository.findAllById(petList);


        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return schedulerRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return schedulerRepository.findAll();
    }

    public List<Schedule> getAllScheduleByPetId(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return schedulerRepository.getAllByPets(pet);
    }
}
