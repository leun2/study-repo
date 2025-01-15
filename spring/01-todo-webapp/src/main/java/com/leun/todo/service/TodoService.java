package com.leun.todo.service;

import com.leun.todo.entity.Todo;
import com.leun.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//    private static Integer todo_count = 0;
//
//    private static List<Todo> todos = new ArrayList<>();
//
//    public List<Todo> findByUser(String username){
//        Predicate<? super Todo> predicate = todo -> todo.getTodo_name().equalsIgnoreCase(username);
//        return todos.stream().filter(predicate).toList();
//    }
//
//    public List<Todo> addTodo(String username, String description, LocalDate localDate){
//        Todo todo = new Todo(++todo_count, username, description, localDate, false);
//        todos.add(todo);
//
//        return todos;
//    }
//
//    public void deleteById(Integer id){
//        Predicate<? super Todo> predicate = todo -> todo.getTodo_id() == id;
//        todos.removeIf(predicate);
//    }
//
//    public Todo findById(Integer id) {
//        Predicate<? super Todo> predicate = todo -> todo.getTodo_id() == id;
//        Todo todo = todos.stream().filter(predicate).findFirst().get();
//
//        return todo;
//    }
//
//    public void updateTodo(@Valid Todo todo) {
//        deleteById(todo.getTodo_id());
//        todos.add(todo);
//    }
//
//    public String getUserName(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return  authentication.getName();
//    }

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
        todoRepository.deleteById(id);
    }

    public Todo findById(Integer id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        return todoOptional.get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getTodoId());
        todoRepository.save(todo);
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }

}
