package com.ardni.sample_spring_boot.service;

import com.ardni.sample_spring_boot.enums.EmployeeSortMode;
import com.ardni.sample_spring_boot.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface EmployeeService {
    void add(Employee employee);
    void update(Employee employee);
    List<Employee> getAll();

    public List<Employee> getAll(EmployeeSortMode sortMode, Integer ageFilter);
    public List<Employee> getAll(EmployeeSortMode sortMode);
    public List<Employee> getAll(Integer ageFilter);

    Employee findByID(Long id);
    void deleteByID(Long id);
    void deleteAll();
}
