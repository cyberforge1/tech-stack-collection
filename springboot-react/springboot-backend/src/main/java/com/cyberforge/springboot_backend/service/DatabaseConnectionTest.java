// File: src/main/java/com/cyberforge/springboot_backend/service/DatabaseConnectionTest.java

package com.cyberforge.springboot_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseConnectionTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void testDbConnection() {
        // Check if environment variables are loaded
        if (url == null || username == null || password == null) {
            System.out.println("One or more database environment variables are not set. Please check your application.properties.");
            return;
        }

        // Print environment variables to confirm
        System.out.println("Database URL: " + url);
        System.out.println("Database Username: " + username);
        System.out.println("Database Password: " + password);

        try {
            // Attempt to establish a connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected to the database.");

            // Get metadata about the connection
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("MySQL Server Version: " + metaData.getDatabaseProductVersion());

            // Print IP address (host part of the URL)
            String host = url.substring(url.indexOf("//") + 2, url.lastIndexOf(":"));
            System.out.println("MySQL Server IP Address: " + host);

            // Execute a simple query to verify the connection
            boolean valid = connection.isValid(2); // timeout in seconds
            if (valid) {
                System.out.println("Connection to the database was successful!");
            } else {
                System.out.println("Connection to the database failed.");
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL: " + e.getMessage());
        }
    }
}
