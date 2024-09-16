// File: src/main/java/com/cyberforge/springboot_backend/controller/TodoController.java

package com.cyberforge.springboot_backend.controller;

import com.cyberforge.springboot_backend.model.Todo;
import com.cyberforge.springboot_backend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    // Constructor Injection
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        Map<String, Object> response = new HashMap<>();
        response.put("todos", todos);  // Return an empty array if no todos exist
        return ResponseEntity.ok(response);
    }
    // GET /todo/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("todo", todo);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();  // Now returning Map<String, Object> to match method signature
            errorResponse.put("error", "Todo not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // POST /todo
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        Map<String, Object> response = new HashMap<>();
        response.put("todo", createdTodo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT /todo/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo);
        if (updatedTodo != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("todo", updatedTodo);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Todo not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // DELETE /todo/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTodo(@PathVariable Long id) {
        boolean deleted = todoService.deleteTodo(id);
        if (deleted) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Todo deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Todo not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
