package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.convertor.ListToJSONConvertor;
import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class EmployeePayrollData {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private String salary;

    @Convert(converter = ListToJSONConvertor.class)
    private List<String> department;

    private String profilePic;
    private LocalDateTime startDate;
    private String notes;
}
