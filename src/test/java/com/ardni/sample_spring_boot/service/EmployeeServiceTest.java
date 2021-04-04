package com.ardni.sample_spring_boot.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ardni.sample_spring_boot.model.Employee;
import com.ardni.sample_spring_boot.repository.EmployeeRepository;
import com.ardni.sample_spring_boot.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImp service;

    @Mock
    EmployeeRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<Employee> list = new ArrayList<Employee>();

        list.add(new Employee(1L, "Ilya", 12.0f, 99));
        list.add(new Employee(2L, "Alex", 12.0f, 19));
        list.add(new Employee(3L, "Steve", 12.0f, 22));

        when(repository.findAll()).thenReturn(list);

        List<Employee> empList = service.getAll();

        assertEquals(3, empList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee employee = new Employee(1L, "Ilya", 12.0f, 99);
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee employeeService = service.findByID(1L);

        assertEquals(employee, employeeService);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void getEmployeeByAgeTest() {
        List<Employee> list = new ArrayList<Employee>();

        list.add(new Employee(2L, "Alex", 12.0f, 19));
        list.add(new Employee(3L, "Steve", 12.0f, 19));

        when(repository.findAllByAge(19)).thenReturn(list);

        List<Employee> listService = service.getAll(19);

        assertEquals(2, listService.size());
        assertEquals(list, listService);
        verify(repository, times(1)).findById(1L);
    }
}