package fr.example;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfiguration {
    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        return (PostgreSQLContainer<?>) new PostgreSQLContainer("postgres:15.4-alpine3.18")
                .withDatabaseName("modularmonolith")
                .withUsername("modularmonolith")
                .withPassword("modularmonolith");
    }

}
