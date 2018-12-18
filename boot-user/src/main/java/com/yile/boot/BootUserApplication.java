package com.yile.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan("com.yile.boot.user.mapper")
@ImportResource({"classpath:*.xml"})
public class BootUserApplication extends BaseApplication{

	public static void main(String[] args) {
		SpringApplication.run(BootUserApplication.class, args);
	}
}
