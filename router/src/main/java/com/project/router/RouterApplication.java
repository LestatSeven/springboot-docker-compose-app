package com.project.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@EnableRabbit
public class RouterApplication {
    public static void main(String[] args) {
        long start = System.nanoTime();
        log.info("http://localhost:8081");
        SpringApplication.run(RouterApplication.class, args);
        log.info("Startup: {} ms", (System.nanoTime() - start) / 1_000_000);
    }

}
