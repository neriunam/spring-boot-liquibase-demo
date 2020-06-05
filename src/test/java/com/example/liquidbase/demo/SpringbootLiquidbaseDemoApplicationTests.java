package com.example.liquidbase.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.liquidbase.demo.util.HsqlDBUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringbootLiquidbaseDemoApplicationTests {

	@BeforeAll
	public static void initialize() {
		log.info("Initialize test");
		HsqlDBUtils.startHsqldbServer();
	}
	
	@Test
	void contextLoads() { }
	
	@AfterAll
	public static void finalizeTest() {
		HsqlDBUtils.stopHsqldbServer();
	}

}
