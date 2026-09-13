package com.love2code.springboot.employees.service;

import com.love2code.springboot.employees.dao.EmployeeRepository;
import com.love2code.springboot.employees.entity.Employee;
import com.love2code.springboot.employees.request.EmployeeRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long theId) {
      Optional<Employee> result = employeeRepository.findById(theId);

      Employee employee = null;

      if (result.isPresent()) {
          employee = result.get();
      } else {
          throw new RuntimeException("Didn't find the id : " + theId);
      }
        return employee;
    }

    @Override
    public Employee save(EmployeeRequest theEmployeeRequest) {
        Employee theEmployee = convertToEmployee(0, theEmployeeRequest);
        return employeeRepository.save(theEmployee);
    }

    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(theEmployee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail());
    }

    @Override
    public void deleteById(long theId) {
        employeeRepository.deleteById(theId);
    }
}
