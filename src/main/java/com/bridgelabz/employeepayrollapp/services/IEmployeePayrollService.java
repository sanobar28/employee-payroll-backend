package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;

import java.util.List;

public interface IEmployeePayrollService {

    List<EmployeePayrollData> getEmployeePayrollData();

    EmployeePayrollData getEmployeePayrollById(int empTd);

    EmployeePayrollData createEmployeePayrollData(
            EmployeePayrollDTO empPayrollDTO);

    EmployeePayrollData updateEmployeePayrollData(
            EmployeePayrollDTO empPayrollDTO);

    void deleteEmployeePayrollData(int empId);
}
