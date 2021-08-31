package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import javassist.NotFoundException;

import java.util.List;

public interface IEmployeePayrollService {

    List<EmployeePayrollData> getEmployeePayrollData();

    EmployeePayrollDTO createEmployeePayrollData(
            EmployeePayrollDTO empPayrollDTO);

    EmployeePayrollDTO updateEmployeePayrollData(int empID,
            EmployeePayrollDTO empPayrollDTO) throws NotFoundException;

    void deleteEmployeePayrollData(int empId);
}
