package com.example.liquidbase.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Departments")
@Data
public class Department {

	@Id
	private Integer id;

	private String name;

}