package com.study.u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UApplication {

	public static void main(String[] args) {
		SpringApplication.run(UApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello spring boot";
	}
}

