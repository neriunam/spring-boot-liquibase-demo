package com.example.liquibase.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.liquibase.demo.entity.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> { }
