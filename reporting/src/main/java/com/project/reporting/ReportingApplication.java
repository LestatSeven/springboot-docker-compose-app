package com.project.reporting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
@EnableRabbit
public class ReportingApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        long start = System.nanoTime();
        log.info("http://localhost:8082");
        SpringApplication.run(ReportingApplication.class, args);
        log.info("Startup: {} ms", (System.nanoTime() - start) / 1_000_000);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/",
                        "classpath:/resources/",
                        "classpath:/static/",
                        "classpath:/public/"
        );
    }
}