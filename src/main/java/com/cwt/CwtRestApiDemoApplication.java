package com.cwt;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CwtRestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CwtRestApiDemoApplication.class, args);
	}

}
