package com.love2code.springboot.employees.controller;

import com.love2code.springboot.employees.entity.Employee;
import com.love2code.springboot.employees.request.EmployeeRequest;
import com.love2code.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "EmployeeREST API Endpoints", description = "Operations Related to Employees")
public class EmployeeRestController {

    //property for dao
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @Operation(summary = "Get all Employees", description = "Retrieve a list of all Employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Get Employee by ID", description = "Retrieve an Employee by ID")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable @Min(value = 1) long empId){
        return employeeService.findById(empId);
    }

    @Operation(summary = "Add new Employee", description = "Insert an Employee")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @Operation(summary = "Update Employee by ID", description = "Update an Employee by ID")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{empId}")
    public Employee updateEmployee(@PathVariable @Min(value = 1) long empId,
                                    @Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.update(empId, employeeRequest);
    }


    @Operation(summary = "Delete an Employee by ID", description = "Delete an Employee by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{empId}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long empId) {
        employeeService.deleteById(empId);
    }


}
