package com.leun.todo.repository;

import com.leun.todo.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId_UserId(Integer userId);

    List<Todo> findByUserId_UserName(String userName);
}
