package com.example.demo;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    Employee employee;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
        employee = Employee.builder()
                .name("Test update name")
                .surname("Test update surname")
                .age(60)
                .weight(50)
                .build();
    }

    @Test
    public void testCreateEmployeeEndpoint() throws Exception {

        String employeeJson = objectMapper.writeValueAsString(employee);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().is(200)
        );

        Mockito.verify(employeeService, Mockito.times(1)).createEmployee(employee);
    }

    @Test
    public void testGetAllEmployeeEndpoint() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/employee/all")
        ).andExpect(status().is(200));

        Mockito.verify(employeeService, Mockito.times(1)).returnAllEmployees();
    }

    @Test
    public void testGetEmployeeById() throws Exception{

        final Integer id = 1;

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/employee/" + id)
        ).andExpect(status().is(200));

        Mockito.verify(employeeService, Mockito.times(1)).returnEmployeeById(id);
    }

    @Test
    public void testDeleteEmployeeById() throws Exception{

        final Integer id = 12897123;

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/employee/" + id)
        ).andExpect(status().is(200));

        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(id);
    }

    @Test
    public void testUpdateEmployeeById() throws Exception{

        final Integer id = 1231908;


        String employeeJson = objectMapper.writeValueAsString(employee);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/employee/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson)
        ).andExpect(status().is(200));

        Mockito.verify(employeeService, Mockito.times(1)).updateEmployee(id, employee);
    }


}
