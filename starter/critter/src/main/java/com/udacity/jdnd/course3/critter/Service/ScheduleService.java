package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    public List<Schedule> getAllScheduleByCustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        return schedulerRepository.getAllByPetsIn(customer.getPets());
    }

    public List<Schedule> getAllScheduleByEmployeeId(Long empId) {
        Employee employee = employeeRepository.getOne(empId);
        System.out.println(employee.getId());
        return schedulerRepository.getAllByEmployees(employee);
    }
}
