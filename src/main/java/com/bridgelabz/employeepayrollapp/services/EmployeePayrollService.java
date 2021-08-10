package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.exception.NotFoundException;
import java.util.List;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();
    }

    @Override
    public EmployeePayrollData getEmployeePayrollById(int empId)  {
        return employeePayrollRepository.findById(empId)
                .orElseThrow(() -> new NotFoundException("User id not found" +empId));
    }

    @Override
    public ResponseDTO createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
       EmployeePayrollData empData = new EmployeePayrollData();
       empData = this.convertEntity(empData, empPayrollDTO);
       empData = employeePayrollRepository.save(empData);
       if (empData != null) {
           return new  ResponseDTO("Data inserted successfully!!");
       } else {
           return new ResponseDTO("insertion failed");
       }
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empID,
                                                         EmployeePayrollDTO empPayrollDTO)  {
        EmployeePayrollData empData = employeePayrollRepository.findById(empID)
                .orElseThrow(() -> new NotFoundException("User not found with this Id: " + empID));;
        empData = this.convertEntity(empData, empPayrollDTO);
        empData.setId(empID);
        return employeePayrollRepository.save(empData);
    }

    @Override
    public ResponseDTO deleteEmployeePayrollData(int empId) {
        employeePayrollRepository.deleteById(empId);
        return new ResponseDTO("Deleted Successfully");
    }

    private EmployeePayrollDTO convertObj (EmployeePayrollData employee) {
        EmployeePayrollDTO employeePayrollDTO = new EmployeePayrollDTO();
        employeePayrollDTO.setName(employee.getName());
        employeePayrollDTO.setGender(employee.getGender());
        employeePayrollDTO.setDepartment(employee.getDepartment());
        employeePayrollDTO.setSalary(employee.getSalary());
        return employeePayrollDTO;
    }

    private EmployeePayrollData convertEntity (EmployeePayrollData empData, EmployeePayrollDTO employeePayrollDTO) {
        empData.setName(employeePayrollDTO.getName());
        empData.setDepartment(employeePayrollDTO.getDepartment());
        empData.setGender(employeePayrollDTO.getGender());
        empData.setSalary(employeePayrollDTO.getSalary());
        return empData;
    }
}
