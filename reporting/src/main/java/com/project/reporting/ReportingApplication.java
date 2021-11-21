package com.project.reporting;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ReportingApplication {

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println("http://localhost:8082");
        SpringApplication.run(ReportingApplication.class, args);
        System.out.println(String.format("Startup: %s ms", (System.nanoTime() - start) / 1_000_000));
    }
}