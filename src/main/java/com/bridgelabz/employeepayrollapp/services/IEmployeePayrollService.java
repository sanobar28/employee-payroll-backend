package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import javassist.NotFoundException;

import java.util.List;

public interface IEmployeePayrollService {

    public List<EmployeePayrollData> getEmployeePayrollData();

    public EmployeePayrollData getEmployeePayrollById(int empTd) throws NotFoundException;

    public ResponseDTO createEmployeePayrollData(
            EmployeePayrollDTO empPayrollDTO);

    public EmployeePayrollData updateEmployeePayrollData(int empID,
            EmployeePayrollDTO empPayrollDTO) throws NotFoundException;

    public ResponseDTO deleteEmployeePayrollData(int empId);
}
