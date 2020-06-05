package com.example.liquidbase.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEES")
@Data
public class Employee {

	@Id
	private Integer id;

	private String firstName;

	private String lastName;
	
}
