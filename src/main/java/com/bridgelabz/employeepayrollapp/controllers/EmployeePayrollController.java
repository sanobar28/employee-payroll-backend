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
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    /**
     * Get data from database
     * @return
     */
    @RequestMapping(value = {"/get"})
    public ResponseEntity<List<EmployeePayrollData>> getEmployeePayrollData() {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollData(), HttpStatus.OK);
    }

    /**
     * Get employee data by ID
     * @param empID
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/get/{empID}")
    public ResponseEntity<EmployeePayrollData> getEmployeePayrollData(@PathVariable int empID) throws NotFoundException {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollById(empID), HttpStatus.OK);
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
        if (e.hasErrors()) {
            List<String> error = e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDTO("Validation Error"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResponseDTO>(employeePayrollService.createEmployeePayrollData(employeePayrollDTO), HttpStatus.OK);
    }

    /**
     * Update employee data by ID
     * @param empID
     * @param employeePayrollDTO
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/update/{empID}")
    public ResponseEntity<EmployeePayrollData> updateEmployeePayrollData(@PathVariable int empID,
           @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) throws NotFoundException {
        return new ResponseEntity<>(employeePayrollService.updateEmployeePayrollData(empID,employeePayrollDTO), HttpStatus.OK);
    }

    /**
     * Delete employee data by ID
     * @param empID
     * @return
     */
    @DeleteMapping("/delete/{empID}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable int empID) {
        return new ResponseEntity<ResponseDTO>(employeePayrollService.deleteEmployeePayrollData(empID), HttpStatus.OK);
    }
}
