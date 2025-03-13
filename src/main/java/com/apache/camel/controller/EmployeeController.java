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
    public ResponseEntity<Object> saveEmployee(Employee employee)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<Object> findAllEmployees(){
        return new ResponseEntity<>(employeeService.findAllEmployees(),HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> findEmployeeByName(@PathVariable String name){
        return new ResponseEntity<>(employeeService.findEmployeeByEmpName(name),HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> removeEmployee(@PathVariable int id){
        employeeService.removeEmployee(id);
        return new ResponseEntity<>("Employee Deleted SuccessFully", HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<?> findAllEmployeesByPagination(
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "empName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){

        return new ResponseEntity<>(employeeService.findAllByPagination(page,size,sortBy,direction),HttpStatus.OK);
    }
}
