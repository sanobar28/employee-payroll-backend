package com.bridgelabz.employeepayrollapp.exception;

import com.bridgelabz.employeepayrollapp.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (EmployeePayrollException.class)
    public ResponseEntity<Error> handle(EmployeePayrollException e) {
        if(e instanceof NotFoundException) {
            return new ResponseEntity<Error>(new Error(e.getMessage(), 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Error>(new Error(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }
}
