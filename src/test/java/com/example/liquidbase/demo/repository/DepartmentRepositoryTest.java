package com.example.liquidbase.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.liquidbase.demo.entity.Department;
import com.example.liquidbase.demo.util.HsqlDBUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
@ActiveProfiles("test")
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private Department newDepartment = null;

	@BeforeAll
	public static void initialize() {
		log.info("Initialize test");
		HsqlDBUtils.startHsqldbServer();
	}
	
	@BeforeEach
	public void beforeEach() {
		//departmentRepository.findAll().forEach(System.out::println);
		log.info("Insert department...");
		newDepartment = insertDepartment();
		log.info("Inserted department: {}", newDepartment);
	}
	
	@Test
	void given_DepartmentRepository_When_findAllMethodInvoked_Then_FoundDepartmentInCollection() {
		Iterator<Department> it = departmentRepository.findAll().iterator();
		boolean found = false;
		while(it.hasNext()) {
			if (it.next().equals(newDepartment)) { found = true; }
		}
		assertTrue(found);
	}
	
	@Test
	void given_DepartmentRepository_When_findByIdMethodInvoked_Then_SucessRetrieveDepartment() {
		Optional<Department> dep = departmentRepository.findById(newDepartment.getId());
		assertTrue(dep.isPresent());
		assertEquals(newDepartment, dep.get());
	}
	
	@Test
	void given_DepartmentRepository_When_deleteMethodInvoked_Then_SucessSavedDepartment() {
		departmentRepository.delete(newDepartment);
		assertTrue(true);
	}
	
	@AfterAll
	public static void finalizeTest() {
		HsqlDBUtils.stopHsqldbServer();
	}
	
	private Department insertDepartment() {
		Department dep = new Department();
		dep.setName("TEST DEPARTMENT at " + new Date().getTime());
		return departmentRepository.save(dep);
	}
	
}
