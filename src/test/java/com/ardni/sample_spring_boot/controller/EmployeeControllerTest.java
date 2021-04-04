package com.ardni.sample_spring_boot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ardni.sample_spring_boot.model.Employee;
import com.ardni.sample_spring_boot.model.EmployeeResponse;
import com.ardni.sample_spring_boot.service.EmployeeServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeServiceImp service;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee employeeRequest = new Employee(1L, "Ilya", 12.0f, 99);

        // when(service.add(any(Employee.class))).d;
        Mockito.doNothing().when(service).add(any(Employee.class));
        mockMvc.perform(post("/addEmployee")
                .content(objectMapper.writeValueAsString(employeeRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        List<Employee> listResponse = new ArrayList<Employee>();
        listResponse.add(new Employee(1L, "Ilya", 12.0f, 99));
        listResponse.add(new Employee(2L, "Alex", 12.0f, 19));
        listResponse.add(new Employee(3L, "Steve", 12.0f, 22));

        EmployeeResponse response = new EmployeeResponse("success", listResponse);

        when(service.getAll()).thenReturn(listResponse);
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllEmployeesWithAgeTest() throws Exception {
        List<Employee> listResponse = new ArrayList<Employee>();
        listResponse.add(new Employee(2L, "Alex", 12.0f, 19));
        listResponse.add(new Employee(3L, "Steve", 12.0f, 19));

        EmployeeResponse response = new EmployeeResponse("success", listResponse);

        when(service.getAll(19)).thenReturn(listResponse);
        mockMvc.perform(get("/employees")
                .param("ageFilter", "19"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}