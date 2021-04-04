package com.ardni.sample_spring_boot.service;

import com.ardni.sample_spring_boot.enums.EmployeeSortMode;
import com.ardni.sample_spring_boot.model.Employee;
import com.ardni.sample_spring_boot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        Employee oldEmployee = employeeRepository.findById(employee.getID()).orElseThrow();
        oldEmployee.setEmployee(employee);
        employeeRepository.save(oldEmployee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAll(EmployeeSortMode sortMode, Integer ageFilter) {
        switch (sortMode.name().toUpperCase()) {
            case ("INCREASE") : return employeeRepository.findAllByAgeOrderByName(ageFilter);
            case ("DECREASE") : return employeeRepository.findAllByAgeOrderByNameDesc(ageFilter);
            default: return employeeRepository.findAll();
        }
    }

    @Override
    public List<Employee> getAll(EmployeeSortMode sortMode) {
        switch (sortMode.name().toUpperCase()) {
            case ("INCREASE") : return employeeRepository.findAllByOrderByName();
            case ("DECREASE") : return employeeRepository.findAllByOrderByNameDesc();
            default: return employeeRepository.findAll();
        }
    }

    @Override
    public List<Employee> getAll(Integer ageFilter) {
        return employeeRepository.findAllByAge(ageFilter);
    }

    @Override
    public Employee findByID(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteByID(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
