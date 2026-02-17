package com.ayush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringSecurityJwtApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApp1Application.class, args);
	}

}
