package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeInfoRepository employeeInfoRepository;

    public List<Employee> returnAllEmployees(){
        log.info("Returning all employees from the database...");
        return (List<Employee>) employeeInfoRepository.findAll();
    }

    public Employee returnEmployeeById(Integer id){
        return employeeInfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public void createEmployee(Employee employee){
        employeeInfoRepository.save(employee);
        log.info("Employee: " + employee + " saved in the database");
    }

    public void deleteEmployee(Integer id){
        Employee employee = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        log.info("Employee " + employee + " has been successfully deleted!");
    }

    public void updateEmployee(Integer id, Employee employee){
        Employee employee1 = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        employee.setId(id);
        employeeInfoRepository.save(employee);
        log.info("Employee " + employee + " successfully updated!");
    }

    public List<Employee> returnAllEmployeesByAge(Integer age){
        return employeeInfoRepository.findAllByAge(age);
    }

    public List<Employee> returnAllEmployeesGreaterThanAge(Integer age){
        return employeeInfoRepository.findAllByAgeGreaterThan(age);
    }

    public List<Employee> returnAllEmployeesByAgeAndWeightGreaterThan(Integer age, Integer weight){
        return employeeInfoRepository.findAllByAgeAndWeightGreaterThan(age, weight);
    }
}
