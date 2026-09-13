package com.love2code.springboot.employees.dao;

import com.love2code.springboot.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //No need to write any code as it automatically include the functions

}
