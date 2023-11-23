package com.example.demo.repository;

import com.example.demo.model.EmployeeAddress;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Table(name = "employee_address")
public interface EmployeeAddressRepository extends CrudRepository<EmployeeAddress, Integer> {

    Optional<EmployeeAddress> findByEmployeeId(Integer employeeId);
}
