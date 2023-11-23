package com.example.demo.controller;

import com.example.demo.DTO.EmployeeAddressDTO;
import com.example.demo.DTOTransformer.EmployeeAddressDTOTransformer;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.service.EmployeeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeAddressController {

    @Autowired
    EmployeeAddressService employeeAddressService;

    @GetMapping("/api/address")
    public ResponseEntity<List<EmployeeAddressDTO>> returnAllAddresses(){
        return ResponseEntity.ok(employeeAddressService.returnAllAddresses());
    }

    @GetMapping("/api/address/{employeeId}")
    public ResponseEntity<EmployeeAddressDTO> returnEmployeeAddress(@PathVariable Integer employeeId){
        return ResponseEntity.ok(employeeAddressService.returnEmployeeAddressById(employeeId));
    }

    @PostMapping("api/address")
    public ResponseEntity<String> createEmployeeAddress(
            @RequestBody EmployeeAddressDTO employeeAddressDTO){
        employeeAddressService.createEmployee(employeeAddressDTO);
        return ResponseEntity.ok("Employee address created! " + employeeAddressDTO);
    }
}
