package com.ardni.sample_spring_boot.controller;

import com.ardni.sample_spring_boot.enums.EmployeeSortMode;
import com.ardni.sample_spring_boot.model.Employee;
import com.ardni.sample_spring_boot.model.EmployeeResponse;
import com.ardni.sample_spring_boot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseBody
    public EmployeeResponse getAllEmployeesSort(
            @RequestParam(required = false) String sortMode,
            @RequestParam(required = false) Integer ageFilter
    ) {
        EmployeeSortMode sortModeEnum;
        if (sortMode != null) {
            try {
                sortModeEnum = EmployeeSortMode.valueOf(sortMode);
            } catch (IllegalArgumentException ex) {
                return new EmployeeResponse("error", "The sortMode parameter must be one of the values: " +
                        Arrays.stream(EmployeeSortMode.values()).map(x -> x.name()).collect(Collectors.joining(", ")));
            }
            if (ageFilter != null)
                return new EmployeeResponse("success", employeeService.getAll(sortModeEnum, ageFilter));
            else
                return new EmployeeResponse("success", employeeService.getAll(sortModeEnum));
        } else {
            if (ageFilter != null)
                return new EmployeeResponse("success", employeeService.getAll(ageFilter));
            else
                return new EmployeeResponse("success", employeeService.getAll());
        }
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public EmployeeResponse getEmployee(@PathVariable(value = "id") Long id) {
        return new EmployeeResponse("success", employeeService.findByID(id));
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestBody @Valid Employee employee) {
        employeeService.add(employee);
        return "success";
    }

    @RequestMapping(value = "/deleteAllEmployees", method = RequestMethod.GET)
    public String deleteAllEmployees() {
        employeeService.deleteAll();
        return "Ok";
    }

    @RequestMapping(value = "/upgradeEmployee", method = RequestMethod.POST)
    public String upgradeEmployee(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
