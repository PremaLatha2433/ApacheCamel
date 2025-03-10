package com.apache.camel.ApacheCamelDemo.service;

import com.apache.camel.ApacheCamelDemo.entities.Employee;
import com.apache.camel.ApacheCamelDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;



    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByEmpName(String empName) {
        return employeeRepository.findEmployeeByEmpName(empName);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void removeEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }
}
