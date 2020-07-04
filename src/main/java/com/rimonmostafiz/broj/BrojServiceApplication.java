package com.rimonmostafiz.broj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Rimon Mostafiz
 */
@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class BrojServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrojServiceApplication.class, args);
	}

}
