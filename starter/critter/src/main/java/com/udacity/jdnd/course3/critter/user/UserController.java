package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Service.PetService;
import com.udacity.jdnd.course3.critter.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        return convertCustomerToCustomerDTO(userService.saveCustomer(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = userService.findAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();

        for(int i=0; i<customers.size(); i++)
        {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customers.get(i).getId());
            customerDTO.setName(customers.get(i).getName());
            customerDTO.setNotes((customers.get(i).getNotes()));
            customerDTO.setPhoneNumber((customers.get(i).getPhoneNumber()));
            customerDTO.setPetIds(petService.findPetsByOwnerId(customers.get(i).getId())
                    .stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = userService.getCustomerByPetId(petId);
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes((customer.getNotes()));
        customerDTO.setPhoneNumber((customer.getPhoneNumber()));

        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        return convertEmployeeToEmployeeDTO(userService.saveEmployee(employee));
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable Long employeeId) {
        Employee employee = userService.findEmployeeById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());

        return employeeDTO;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable Long employeeId) {
        userService.findEmployeeByIdAndSetAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = userService.findAllEmployeeAvailable(employeeDTO.getDate(), employeeDTO.getSkills());
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> convertEmployeeToEmployeeDTO(employee)).collect(Collectors.toList());
        return employeeDTOS;
    }

    private Customer connvertCustomerDTOtoCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }


    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee emp) {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(emp.getId());
        employeeDTO.setName(emp.getName());
        employeeDTO.setSkills(emp.getSkills());
        employeeDTO.setDaysAvailable(emp.getDaysAvailable());
        return employeeDTO;
    }
}
