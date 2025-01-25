package com.employee.employeeservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Department {

    private String name;
    private String hod;
    private int numberOfStaff;


    public Department() {
    }

    public Department(String name, String hod, int numberOfStaff) {
        this.name = name;
        this.hod = hod;
        this.numberOfStaff = numberOfStaff;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHod() {
        return hod;
    }

    public void setHod(String hod) {
        this.hod = hod;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(int numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", hod='" + hod + '\'' +
                ", numberOfStaff=" + numberOfStaff +
                '}';
    }
}
