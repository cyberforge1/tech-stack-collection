// File: src/main/java/com/cyberforge/springboot_backend/controller/HelloWorldController.java

package com.cyberforge.springboot_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    // GET /helloworld
    @GetMapping("/helloworld")
    public Map<String, String> helloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "World!");
        return response;
    }

    // GET /
    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "World!");
        return response;
    }
}
