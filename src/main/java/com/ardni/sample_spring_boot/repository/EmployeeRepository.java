package com.ardni.sample_spring_boot.repository;

import com.ardni.sample_spring_boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByAge(Integer age);
    List<Employee> findAllByAgeOrderByName(Integer age);
    List<Employee> findAllByAgeOrderByNameDesc(Integer age);
    List<Employee> findAllByOrderByNameDesc();
    List<Employee> findAllByOrderByName();
}
