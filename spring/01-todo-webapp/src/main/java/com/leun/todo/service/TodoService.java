package com.leun.todo.service;

import com.leun.todo.entity.Todo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(1, "Lee", "Learn AWS", LocalDateTime.now(), false));
        todos.add(new Todo(2, "Lee", "Learn DevOps", LocalDateTime.now(), false));
        todos.add(new Todo(3, "Lee", "Learn Full Stack Dev", LocalDateTime.now(), false));
    }

    public List<Todo> findByUser(String user){
        return todos;
    }

}
