/**
 * @author Sanobar Mujawar
 * @since 09.08.21
 *
 * Purpose: Employee payroll backend for making
 * REST-API calls to database.
 */



package com.bridgelabz.employeepayrollapp.controllers;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    /**
     * Get data from database
     * @return
     */
    @RequestMapping(value = {"/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<EmployeePayrollData> employeeList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO responseDTO = new ResponseDTO("Fetched all Employee Payroll Details", employeeList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    /**
     * Create valid employee data in database
     * @param employeePayrollDTO
     * @param e  throws validation error message
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO, BindingResult e) {
        EmployeePayrollDTO addData = employeePayrollService.createEmployeePayrollData(employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Employee Payroll Details",  addData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Update employee data by ID
     * @param empID
     * @param employeePayrollDTO
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/update/{empID}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable int empID,
           @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) throws NotFoundException {
        EmployeePayrollDTO updatedData = employeePayrollService.updateEmployeePayrollData(empID, employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated by ID : Employee Payroll Details", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete employee data by ID
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEmployeeDetails(@PathVariable(name = "id") int id) {
        employeePayrollService.deleteEmployeePayrollData(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted by ID : Employee Payroll Details", null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
