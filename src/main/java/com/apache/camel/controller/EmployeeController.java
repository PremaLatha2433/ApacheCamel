package com.apache.camel.controller;

import com.apache.camel.entities.Employee;
import com.apache.camel.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {


    private final  EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(Employee employee)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<?> findAllEmployees(){
        return new ResponseEntity<>(employeeService.findAllEmployees(),HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findEmployeeByName(@PathVariable String name){
        return new ResponseEntity<>(employeeService.findEmployeeByEmpName(name),HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> removeEmployee(@PathVariable int id){
        employeeService.removeEmployee(id);
        ResponseEntity responseEntity = new ResponseEntity<>("Employee Deleted SuccessFully", HttpStatus.OK);
        return responseEntity;
    }
}
