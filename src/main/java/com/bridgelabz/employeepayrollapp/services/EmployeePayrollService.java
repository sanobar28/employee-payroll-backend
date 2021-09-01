package com.bridgelabz.employeepayrollapp.services;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.exception.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get employee payroll data
     *
     * @return
     */
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollRepository.findAll().stream()
                .map(contact -> modelMapper.map(contact, EmployeePayrollData.class))
                .collect(Collectors.toList());
    }


    /**
     * Method to create employee payroll data
     *
     * @param empPayrollDTO
     * @return
     */
    @Override
    public EmployeePayrollDTO createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData employeeRequest = modelMapper.map(empPayrollDTO, EmployeePayrollData.class);
        employeePayrollRepository.save(employeeRequest);
        return empPayrollDTO;
    }

    /**
     * Method to update employee data for given id
     *
     * @param empID
     * @param empPayrollDTO
     * @return
     */
    @Override
    public EmployeePayrollDTO updateEmployeePayrollData(int empID,
                                                        EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollDTO employeeResponse = null;
        if (empID > 0) {
            EmployeePayrollData empData = findEmployeeById(empID);
            String[] ignoreFields = {"id", "startDate"};
            BeanUtils.copyProperties(empPayrollDTO, empData, ignoreFields);
            employeePayrollRepository.save(empData);
            employeeResponse = modelMapper.map(empData, EmployeePayrollDTO.class);
        }
        return employeeResponse;
    }

    /**
     * Method to delete employee data by id
     *
     * @param empId
     * @return
     */
    @Override
    public void deleteEmployeePayrollData(int empId) {
        EmployeePayrollData employeePayrollData = findEmployeeById(empId);
        employeePayrollRepository.deleteById(empId);
    }

    /**
     * Method to find employee by id
     *
     * @param id
     * @return
     */
    private EmployeePayrollData findEmployeeById(int id) {
        return employeePayrollRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Unable to find any Employee Payroll detail!"));
    }

}
