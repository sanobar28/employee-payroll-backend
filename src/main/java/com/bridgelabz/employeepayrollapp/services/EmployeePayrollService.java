package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.exception.EmployeePayrollException;
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

    /**
     * Method to get employee payroll data
     * @return
     */
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();
    }

    /**
     * Method to get employee data by id
     * @param empId
     * @return
     */
    @Override
    public EmployeePayrollData getEmployeePayrollById(int empId)  {
        return employeePayrollRepository.findById(empId)
                .orElseThrow(() -> new NotFoundException("User id not found" +empId));
    }

    /**
     * Method to create employee payroll data
     * @param empPayrollDTO
     * @return
     */
    @Override
    public ResponseDTO createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
       EmployeePayrollData empData = new EmployeePayrollData();
       empData = this.convertEntity(empData, empPayrollDTO);
       empData = employeePayrollRepository.save(empData);
       if (empData != null) {
           return new  ResponseDTO("Data inserted successfully!!");
       } else {
           throw new EmployeePayrollException("Insertion failed");
       }
    }

    /**
     * Method to update employee data for given id
     * @param empID
     * @param empPayrollDTO
     * @return
     */
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empID,
                                                         EmployeePayrollDTO empPayrollDTO)  {
        EmployeePayrollData empData = employeePayrollRepository.findById(empID)
                .orElseThrow(() -> new NotFoundException("User not found with this Id: " + empID));
        empData = this.convertEntity(empData, empPayrollDTO);
        empData.setId(empID);
        return employeePayrollRepository.save(empData);
    }

    /**
     * Method to delete employee data by id
     * @param empId
     * @return
     */
    @Override
    public ResponseDTO deleteEmployeePayrollData(int empId) {
        employeePayrollRepository.deleteById(empId);
        return new ResponseDTO("Deleted Successfully");
    }

    /**
     * Method to convert employee payroll data to transfer object
     * @param empData
     * @param employeePayrollDTO
     * @return
     */
    private EmployeePayrollData convertEntity (EmployeePayrollData empData, EmployeePayrollDTO employeePayrollDTO) {
        empData.setName(employeePayrollDTO.getName());
        empData.setDepartment(employeePayrollDTO.getDepartment());
        empData.setGender(employeePayrollDTO.getGender());
        empData.setSalary(employeePayrollDTO.getSalary());
        return empData;
    }
}
