package com.employee.employeeservice.controller;

import com.employee.employeeservice.dto.EmployeeDto;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;




    @PostMapping("newemployee")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto){

        return employeeService.createEmployee(employeeDto);
    }


    @GetMapping("/{id}")
    public String getEmployee(@PathVariable int id){

        return employeeService.getEmployee(id);
    }





}
