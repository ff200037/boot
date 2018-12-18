package com.yile.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BootConcurrencyApplication{

	public static void main(String[] args) {
		SpringApplication.run(BootConcurrencyApplication.class, args);
	}

}

