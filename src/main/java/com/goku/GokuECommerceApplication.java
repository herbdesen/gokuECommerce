package com.goku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
@ComponentScan(basePackages = { "com.goku", "com.goku.api" , "com.goku.config"})
public class GokuECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GokuECommerceApplication.class, args);
	}

}
