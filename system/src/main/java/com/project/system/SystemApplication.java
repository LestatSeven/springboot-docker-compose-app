package com.project.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		long start = System.nanoTime();
		log.info("http://localhost:8080");
		SpringApplication.run(SystemApplication.class, args);
		log.info("Startup: {} ms", (System.nanoTime() - start) / 1_000_000);
	}
}
