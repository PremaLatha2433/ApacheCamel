package com.apache.camel.service;

import com.apache.camel.entities.Employee;
import com.apache.camel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Employee> findAllByPagination(int page, int size, String sortBy, String direction)
    {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
        return employeeRepository.findAll(pageable);
    }
}
