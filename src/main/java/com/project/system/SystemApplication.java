package com.project.system;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println("http://localhost:8080");
		SpringApplication.run(SystemApplication.class, args);
		System.out.println(String.format("Startup: %s ms", (System.nanoTime() - start) / 1_000_000));
	}

	/*
	@RestController
	@RequestMapping("/api")
	@RequiredArgsConstructor
	@Api("Тестовый контроллер")
	public static class HelloController {
		@GetMapping("/hello")
		public String hello() {
			return "Ok!";
		}
	}
	 */
}
