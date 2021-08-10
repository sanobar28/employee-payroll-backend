package com.bridgelabz.employeepayrollapp.controllers;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<String> getEmployeePayrollData() {
        return new ResponseEntity<String>("Get call Success", HttpStatus.OK);
    }

    @GetMapping("/get/{empID}")
    public ResponseEntity<String> getEmployeePayrollData(@PathVariable("empID") int empID) {
        return new ResponseEntity<String>("Get call Success for id: " + empID, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addEmployeePayrollData(
            @RequestBody EmployeePayrollDTO employeePayrollDTO) {
        return new ResponseEntity<String>("Created employee payroll data for: " + employeePayrollDTO, HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<String> updateEmployeePayrollData(
            @RequestBody EmployeePayrollDTO employeePayrollDTO) {
        return new ResponseEntity<String>("Updated employee payroll data for: " +employeePayrollDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empID}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("empID") int empID) {
        return new ResponseEntity<String>("Delete call success for id: " +empID, HttpStatus.OK);
    }
}
