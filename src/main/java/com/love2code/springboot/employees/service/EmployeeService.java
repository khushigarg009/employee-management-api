package com.love2code.springboot.employees.service;

import com.love2code.springboot.employees.entity.Employee;
import com.love2code.springboot.employees.request.EmployeeRequest;

import java.util.List;
public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(long theId);

    Employee save(EmployeeRequest theEmployeeRequest) ;

    Employee update(long id, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteById(long theId);
}
