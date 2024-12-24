package com.sample.basics.Basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.sample.basics.Pojo")
@EnableJpaRepositories(basePackages = "com.sample.basics.JPA")
@ComponentScan(basePackages = {"com.sample.basics.Controller", 
        "com.sample.basics.Service", 
        "com.sample.basics.Pojo"})
public class BasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicsApplication.class, args);
	}

}