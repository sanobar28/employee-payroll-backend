package com.bridgelabz.employeepayrollapp.controllers;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @RequestMapping(value = {"/get"})
    public ResponseEntity<List<EmployeePayrollData>> getEmployeePayrollData() {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollData(), HttpStatus.OK);
    }

    @GetMapping("/get/{empID}")
    public ResponseEntity<EmployeePayrollData> getEmployeePayrollData(@PathVariable int empID) throws NotFoundException {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollById(empID), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {
        return new ResponseEntity<ResponseDTO>(employeePayrollService.createEmployeePayrollData(employeePayrollDTO), HttpStatus.OK);
    }

    @PutMapping("/update/{empID}")
    public ResponseEntity<EmployeePayrollData> updateEmployeePayrollData(@PathVariable int empID,
           @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) throws NotFoundException {
        return new ResponseEntity<>(employeePayrollService.updateEmployeePayrollData(empID,employeePayrollDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empID}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable int empID) {
        return new ResponseEntity<ResponseDTO>(employeePayrollService.deleteEmployeePayrollData(empID), HttpStatus.OK);
    }
}
