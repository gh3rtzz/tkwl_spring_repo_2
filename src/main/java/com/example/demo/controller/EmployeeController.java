package com.example.demo.controller;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/api/employee/all")
    public ResponseEntity<List<Employee>> returnAllEmployees(){
        return ResponseEntity.ok(employeeService.returnAllEmployees());
    }

    @GetMapping("/api/employee/{id}")
    public ResponseEntity<Employee> returnEmployeeById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.returnEmployeeById(id));
    }

    @GetMapping("/api/employee/age/{age}")
    public ResponseEntity<List<Employee>> returnAllEmployeesByAge(@PathVariable Integer age){
        return ResponseEntity.ok(employeeService.returnAllEmployeesByAge(age));
    }

    @GetMapping("/api/employee/age/greater/{age}")
    public ResponseEntity<List<Employee>> returnAllEmployeesGreaterThanAge(@PathVariable Integer age){
        return ResponseEntity.ok(employeeService.returnAllEmployeesGreaterThanAge(age));
    }

    @PostMapping("/api/employee")
    public ResponseEntity<String> createNewEmployee(@RequestBody @Valid Employee employee){
        employeeService.createEmployee(employee);
        return ResponseEntity.ok("Employee " + employee + " successfully created!");
    }

    @DeleteMapping("/api/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee with id " + id + " has been deleted!");
    }

    @PutMapping("/api/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){

        employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok("Employee with id " + id + " has been updated!");
    }

    @GetMapping("/api/employee/{age}/{weight}")
    public ResponseEntity<List<Employee>> returnEmployeeByAgeAndWeightGreaterThan(@PathVariable Integer age, @PathVariable Integer weight){
        return ResponseEntity.ok(employeeService.returnAllEmployeesByAgeAndWeightGreaterThan(age, weight));
    }
}
