package fr.example;

import org.springframework.boot.SpringApplication;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Modulith
@EnableAsync
@EnableScheduling
public class SpringModulithApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringModulithApplication.class, args);
    }
}
