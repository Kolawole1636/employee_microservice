package com.employee.employeeservice.service;


import com.employee.employeeservice.dto.Department;
import com.employee.employeeservice.dto.EmployeeDto;
import com.employee.employeeservice.model.Employee;
import com.employee.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;


    public Employee createEmployee(EmployeeDto employeeDto) {

        double eSalary =0;
        double eTax =0;
        double eBalance =0;

        if(employeeDto.getYoe()<=2){

            eSalary = 5000;
            eTax= 0.05;
            eBalance =  eSalary-(eSalary*eTax);

        }

        else if(employeeDto.getYoe()>2 && employeeDto.getYoe()<=5){
            eSalary = 10000;
            eTax= 0.1;
            eBalance =  eSalary-(eSalary*eTax);
        }

        else if(employeeDto.getYoe()>5 && employeeDto.getYoe()<=10){
            eSalary = 15000;
            eTax= 0.15;
            eBalance =  eSalary-(eSalary*eTax);
        }

        else if(employeeDto.getYoe()>10 && employeeDto.getYoe()<=15){
            eSalary = 20000;
            eTax= 0.2;
            eBalance =  eSalary-(eSalary*eTax);
        }

        else{

            eSalary = 40000;
            eTax= 0.25;
            eBalance =  eSalary-(eSalary*eTax);

        }

        Employee employee = new Employee();


        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setAge(employeeDto.getAge());
        employee.setYoe(employeeDto.getYoe());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        employee.setSalary(eSalary);
        employee.setTax((eTax));
        employee.setMonthlyBalance(eBalance);


        employee = employeeRepository.save(employee);

        String departmentServiceUrl = "http://department-service/";

        String url = departmentServiceUrl + "department/" + employee.getDepartmentId() + "/update";
        restTemplate.put(url + "?incrementBy=1", null);

        return employee;

    }



    @CircuitBreaker(name = "departmentService", fallbackMethod = "fallbackGetDepartment")
    public String getDepartment(int departmentId) {
        String url = "http://department-service/department/" + departmentId;
        Department department= restTemplate.getForObject(url, Department.class);

        return department.getName();
    }

    public String fallbackGetDepartment(int departmentId, Throwable throwable) {
        return "Fallback: Department service is unavailable";
    }




    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Delete the employee
        employeeRepository.deleteById(id);

        // Update the department's staff count
        String departmentServiceUrl = "http://department-service/";

        String url = departmentServiceUrl + "department/" + employee.getDepartmentId() + "/update";
        restTemplate.put(url + "?incrementBy=-1", null);
    }




}
