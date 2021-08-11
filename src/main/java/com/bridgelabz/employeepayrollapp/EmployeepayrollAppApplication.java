package com.bridgelabz.employeepayrollapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@Slf4j
public class EmployeepayrollAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeepayrollAppApplication.class, args);
		log.info("Employee Payroll App Started");
	}
}
