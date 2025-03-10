package com.apache.camel.ApacheCamelDemo.repository;

import com.apache.camel.ApacheCamelDemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findEmployeeByEmpName(String empName);
}

