package com.leun.todo.repository;

import com.leun.todo.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByTodoName(String todoName);
}
