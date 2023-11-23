package com.example.demo.DTOTransformer;

import com.example.demo.DTO.EmployeeAddressDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAddress;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAddressDTOTransformer {

    public EmployeeAddressDTO convertToDTO(EmployeeAddress employeeAddress){

        return EmployeeAddressDTO.builder()
                .country(employeeAddress.getCountry())
                .city(employeeAddress.getCity())
                .addressLine1(employeeAddress.getAddressLine1())
                .addressLine2(employeeAddress.getAddressLine2())
                .zipCode(employeeAddress.getZipCode())
                .fkEmployee(employeeAddress.getEmployee().getId())
                .build();
    }

    public EmployeeAddress convertToDBModel(EmployeeAddressDTO employeeAddressDTO, Employee employee){

        return EmployeeAddress.builder()
                .country(employeeAddressDTO.getCountry())
                .city(employeeAddressDTO.getCity())
                .addressLine1(employeeAddressDTO.getAddressLine1())
                .addressLine2(employeeAddressDTO.getAddressLine2())
                .zipCode(employeeAddressDTO.getZipCode())
                .employee(employee)
                .build();
    }
}
