package com.leun.todo.service;

import com.leun.todo.entity.Todo;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private static Integer todo_count = 0;

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(++todo_count, "Lee", "Learn AWS", LocalDate.now(), false));
        todos.add(new Todo(++todo_count, "Lee", "Learn DevOps", LocalDate.now(), false));
        todos.add(new Todo(++todo_count, "Lee", "Learn Full Stack Dev", LocalDate.now(), false));
    }

    public List<Todo> findByUser(String user){
        return todos;
    }

    public List<Todo> addTodo(String username, String description, LocalDate localDate){
        Todo todo = new Todo(++todo_count, username, description, localDate, false);
        todos.add(todo);

        return todos;
    }

    public void deleteById(Integer id){
        Predicate<? super Todo> predicate = todo -> todo.getTodo_id() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(Integer id) {
        Predicate<? super Todo> predicate = todo -> todo.getTodo_id() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();

        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getTodo_id());
        todos.add(todo);
    }
}
