package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeInfoRepository;
import com.example.demo.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeInfoRepository employeeInfoRepository;

    @Mock
    EmployeeService employeeService;

    @Test
    public void testCreateEmployee(){

        Employee employee = Employee.builder()
                .name("Test name")
                .surname("Test surname")
                .age(30)
                .weight(90)
                .build();

        employeeService.createEmployee(employee);

        Mockito.verify(employeeInfoRepository, Mockito.times(1)).save(employee);

    }

    @Test
    public void testFindEmployee(){
        Employee employee = Employee.builder()
                .name("Test name")
                .surname("Test surname")
                .age(30)
                .weight(90)
                .build();

        Mockito.when(employeeService.returnAllEmployees()).thenReturn(List.of(employee));
        List<Employee> employeeList = employeeService.returnAllEmployees();

        Assert.assertEquals(List.of(employee), employeeList);
    }


}
