package com.example.liquibase.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.liquibase.demo.util.HsqlDBUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class SpringbootLiquidbaseDemoApplicationTests {

	@BeforeAll
	public static void initialize() {
		log.info("Initialize test");
		HsqlDBUtils.startHsqldbServer();
	}
	
	@Test
	void contextLoads() { }
	
}
