package com.anurag.openapidemo.controller;

import com.anurag.openapidemo.entity.Employee;
import com.anurag.openapidemo.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    @Tag(name="get")
    public List<Employee> findAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    @Tag(name="get")
    public Employee getEmployee(@Parameter(
            description = "ID of employee to be retrieved",
            required = true)
                                @PathVariable int employeeId) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
        return employee;
    }

    @PostMapping("/employees")
    @Tag(name="post")
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        employee.setId(0);
        Employee newEmployee = repository.save(employee);
        return newEmployee;
    }

    @PutMapping("/employees")
    @Tag(name="put")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee theEmployee = repository.save(employee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    @Operation(summary="!!!Delete!!!", description = "description-description-description-description")
    @Tag(name="delete")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee id not found - " + employeeId));
        repository.delete(employee);
        return "Deleted employee with id: " + employeeId;
    }

}
