// File: src/main/java/com/cyberforge/springboot_backend/repository/TodoRepository.java

package com.cyberforge.springboot_backend.repository;

import com.cyberforge.springboot_backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
