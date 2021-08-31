package com.bridgelabz.employeepayrollapp.dto;

import com.bridgelabz.employeepayrollapp.convertor.ListToJSONConvertor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePayrollDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}", message = "Employee name Invalid")
    private String name;
    private String gender;

    @Min(value = 1000, message = "Min salary should be more than 1000")
    private String salary;


    //@Convert(converter = ListToJSONConvertor.class)
    private List<String> department;

    private String profilePic;
    private LocalDateTime startDate;
    private String notes;

}
