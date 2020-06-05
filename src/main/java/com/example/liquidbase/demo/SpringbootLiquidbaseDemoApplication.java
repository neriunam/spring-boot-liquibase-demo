package com.example.liquidbase.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.liquidbase.demo.repository.DepartmentRepository;
import com.example.liquidbase.demo.util.HsqlDBUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringbootLiquidbaseDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = null;
		try {
			
			HsqlDBUtils.startHsqldbServer();
			ctx = SpringApplication.run(SpringbootLiquidbaseDemoApplication.class, args);
			
			log.info("List departments");
			ctx.getBean(DepartmentRepository.class).findAll().forEach(System.out::println);

			log.info("Running...");
		} catch (Exception e) {
			log.error("Exepcion", e);
			HsqlDBUtils.stopHsqldbServer();
			ctx.close();
		}
	}

}
