package com.example.liquidbase.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.liquidbase.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> { }
