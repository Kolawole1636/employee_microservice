package com.employee.employeeservice.controller;

import com.employee.employeeservice.dto.EmployeeDto;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.repository.EmployeeRepository;
import com.employee.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;



    @PostMapping("/newemployee")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto){

        return employeeService.createEmployee(employeeDto);
    }


   /* @GetMapping("/{id}")
    public String getEmployee(@PathVariable int id){

        return employeeService.getEmployee(id);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<String> getEmployeeWithDepartment(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).get();
        String department = employeeService.getDepartment(employee.getDepartmentId());
        return ResponseEntity.ok("Employee's Name: " + employee.getFirstName() + "\n"
                + "Employee's Salary: " + employee.getSalary() +"\n"
                +  "Employee's Email: " + employee.getEmail() +"\n"
                + "Employee's Department: " + department);


    }


    @DeleteMapping("{/id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);

    }





}
