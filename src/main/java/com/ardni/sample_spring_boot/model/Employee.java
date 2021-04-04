package com.ardni.sample_spring_boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private Float salary;
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    public Employee(String name, Float salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public void setEmployee(Employee newEmployee) {
        this.name = newEmployee.name;
        this.salary = newEmployee.salary;
        this.age = newEmployee.age;
    }
}
