package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeInfoRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAllByAge(Integer age);

    List<Employee> findAllByAgeGreaterThan(Integer age);

    @Query("SELECT e FROM Employee e WHERE e.age = :age AND e.weight >= :weight")
    List<Employee> findAllByAgeAndWeightGreaterThan(Integer age, Integer weight);

//    @Query(value = "SELECT * FROM EMPLOYEE_INFO WHERE AGE = ? AND WEIGHT >= ?", nativeQuery = true)
//    List<Employee> findAllByAgeAndWeightGreaterThan(Integer age, Integer weight);

}
