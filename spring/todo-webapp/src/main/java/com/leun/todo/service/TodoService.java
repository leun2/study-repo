package com.leun.todo.service;

import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findByName(String name){

        List<Todo> todos = todoRepository.findByTodoName(name);
        return todos;
    }

    public List<Todo> addTodo(String name, String description, LocalDate localDate){
        Todo todo = new Todo(name, description, localDate, false);
        todoRepository.save(todo);
        List<Todo> todos = todoRepository.findByTodoName(name);

        return todos;
    }

    public void deleteById(Integer id){
        Todo todo = findById(id);

        todoRepository.deleteById(todo.getTodoId());
    }

    public Todo findById(Integer id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        todoOptional
            .orElseThrow(() -> new NoSuchElementException("Todo with ID " + id + " not found"));

        return todoOptional.get();
    }

    public void updateTodo(@Valid Todo todo) {
        todoRepository.save(todo);
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }

}
