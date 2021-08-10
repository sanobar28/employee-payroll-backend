package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import lombok.Data;

@Data
public class EmployeePayrollData {

    private int employeeID;
    private String name;
    private long salary;

    public EmployeePayrollData(int empID, EmployeePayrollDTO empPayrollDTO) {
        this.employeeID = empID;
        this.name = empPayrollDTO.name;
        this.salary = empPayrollDTO.salary;
    }

    public EmployeePayrollData() {
    }
}
