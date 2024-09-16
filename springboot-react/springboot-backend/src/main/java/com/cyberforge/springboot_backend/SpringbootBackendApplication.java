// File: src/main/java/com/cyberforge/springboot_backend/SpringbootBackendApplication.java

package com.cyberforge.springboot_backend;

import com.cyberforge.springboot_backend.service.DatabaseConnectionTest;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringbootBackendApplication {

    @Autowired
    private DatabaseConnectionTest databaseConnectionTest;

    public static void main(String[] args) {
        // Load environment variables from the .env file
        Dotenv dotenv = Dotenv.configure()
            .directory("/Users/softdev/Desktop/github-projects/springboot-react/springboot-backend") // Specify the directory where .env is located
            .load();

        // Optionally, print to verify if .env variables are loaded
        System.out.println("MYSQL_HOST: " + dotenv.get("MYSQL_HOST"));
        System.out.println("MYSQL_PORT: " + dotenv.get("MYSQL_PORT"));
        
        // Start Spring Boot application
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }

    @PostConstruct
    public void onStartup() {
        databaseConnectionTest.testDbConnection();
    }
}
