package com.apache.camel.entities;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_id")
    private int empId;

    @Column(name="emp_name")
    private String empName;

    @Column(name="designation")
    private String designation;

    @Column(name="salary")
    private double salary;
}