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
import com.example.liquidbase.demo.entity.Employee;
import com.example.liquidbase.demo.util.HsqlDBUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee newEmployee = null;

	@BeforeAll
	public static void initialize() {
		log.info("Initialize test");
		HsqlDBUtils.startHsqldbServer();
	}
	
	@BeforeEach
	public void beforeEach() {
		employeeRepository.findAll().forEach(System.out::println);
		log.info("Insert employee...");
		newEmployee = insertEmployee();
		log.info("Inserted employee: {}", newEmployee);
	}
	
	@Test
	void given_EmployeeRepository_When_findAllMethodInvoked_Then_FoundEmployeeInCollection() {
		Iterator<Employee> it = employeeRepository.findAll().iterator();
		boolean found = false;
		while(it.hasNext()) {
			if (it.next().equals(newEmployee)) { found = true; }
		}
		assertTrue(found);
	}
	
	@Test
	void given_EmployeeRepository_When_findByIdMethodInvoked_Then_SucessRetrieveEmployee() {
		Optional<Employee> dep = employeeRepository.findById(newEmployee.getId());
		assertTrue(dep.isPresent());
		assertEquals(newEmployee, dep.get());
	}
	
	@Test
	void given_EmployeeRepository_When_deleteMethodInvoked_Then_SucessDeletedEmployee() {
		employeeRepository.delete(newEmployee);
		assertTrue(true);
	}
	
	@AfterAll
	public static void finalizeTest() {
		HsqlDBUtils.stopHsqldbServer();
	}
	
	private Employee insertEmployee() {
		Long time = new Date().getTime();
		Department dep = new Department();
		dep.setName("TEST DEPARTMENT at " + time);
		departmentRepository.save(dep);
		log.info("Saved department: {}", dep);
		Employee emp = new Employee();
		emp.setDepartment(dep);
		emp.setFirstName("TEST EMPLOYEE at" + time);
		emp.setLastName("TEST LAST NAME");
		return employeeRepository.save(emp);
	}
	
}
