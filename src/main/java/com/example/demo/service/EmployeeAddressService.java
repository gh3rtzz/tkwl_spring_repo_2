package com.example.demo.service;

import com.example.demo.DTO.EmployeeAddressDTO;
import com.example.demo.DTOTransformer.EmployeeAddressDTOTransformer;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import com.example.demo.repository.EmployeeAddressRepository;
import com.example.demo.repository.EmployeeInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeAddressService {

    @Autowired
    EmployeeAddressRepository employeeAddressRepository;
    @Autowired
    EmployeeInfoRepository employeeInfoRepository;
    @Autowired
    EmployeeAddressDTOTransformer employeeAddressDTOTransformer;

    public List<EmployeeAddressDTO> returnAllAddresses(){
        List<EmployeeAddress> employeeAddressList = (List<EmployeeAddress>) employeeAddressRepository.findAll();

        return employeeAddressList.stream()
                .map(e -> employeeAddressDTOTransformer.convertToDTO(e))
                .collect(Collectors.toList());
    }

    public EmployeeAddressDTO returnEmployeeAddressById(Integer employeeId){
        return employeeAddressDTOTransformer.convertToDTO(employeeAddressRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    public void createEmployee(EmployeeAddressDTO employeeAddressDTO){
        Employee employee = employeeInfoRepository.findById(employeeAddressDTO.getFkEmployee())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        EmployeeAddress employeeAddress = employeeAddressDTOTransformer.convertToDBModel(employeeAddressDTO, employee);
        employeeAddressRepository.save(employeeAddress);
    }
}
